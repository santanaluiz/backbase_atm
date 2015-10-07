package com.backbase.test.atmlocator.services;

import com.backbase.test.atmlocator.exceptions.ATMParseException;
import com.backbase.test.atmlocator.models.ATM;
import com.backbase.test.atmlocator.util.HttpRequestBuilder;
import com.backbase.test.atmlocator.util.JSONParser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by luizsantana on 10/6/15.
 */
@Component
public class ATMService {

    public static final String ATM_SERVICE_URL = "https://www.ing.nl/api/locator/atms/";

    public List<ATM> loadAllATMs() {
        String allAtmsJson = getAllAtmsJSON();
        try {
            return JSONParser
                    .init(allAtmsJson)
                    .setEntityClass(ATM.class)
                    .parseList();
        } catch (ATMParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ATM> searchATMs(String city, SearchType searchType) {
        List<ATM> atms = loadAllATMs();

        return filterAtmsToList(city, searchType, atms);
    }

    private List<ATM> filterAtmsToList(String city, SearchType searchType, List<ATM> atms) {
        return atms
            .stream()
            .filter(getPredicateBySearchType(city.toLowerCase(), searchType))
            .collect(Collectors.toList());
    }

    private Predicate<ATM> getPredicateBySearchType(String searchParam, SearchType searchType) {
        return atm -> {
            if (isAnInvalidATMForSearch(atm)) {
                return false;
            }

            String atmCity = atm.getAddress().getCity().toLowerCase();
            switch (searchType) {
                case EQUALS:
                    return atmCity.equals(searchParam);
                case CONTAINS:
                    return atmCity.contains(searchParam);
                case STARTS_WITH:
                    return atmCity.startsWith(searchParam);
                case ENDS_WITH:
                    return atmCity.endsWith(searchParam);
            }

            return false;
        };
    }

    private boolean isAnInvalidATMForSearch(ATM atm) {
        return atm.getAddress() == null || atm.getAddress().getCity() == null;
    }

    private String getAllAtmsJSON() {
        return HttpRequestBuilder
                .init(ATM_SERVICE_URL)
                .request();
    }

    public enum SearchType {
        EQUALS,
        CONTAINS,
        STARTS_WITH,
        ENDS_WITH
    }

}
