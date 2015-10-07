package com.backbase.test.atmlocator.services;

import com.backbase.test.atmlocator.AtmLocatorApplication;
import com.backbase.test.atmlocator.models.ATM;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static com.backbase.test.atmlocator.services.ATMService.SearchType.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

/**
 * Created by luizsantana on 10/6/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AtmLocatorApplication.class)
public class ATMServiceTests {

    @Autowired ATMService atmService;

    @Test
    public void whenLoadAllATMS() {
        List<ATM> atms = atmService.loadAllATMs();

        assertThat(atms, is(notNullValue()));
        assertThat(atms.isEmpty(), not(true));
        assertThat(atms.size(), is(greaterThan(3)));
    }

    @Test
    public void whenSearchForInvalidCity() {
        List<ATM> atms = atmService.searchATMs("HELLO WORLD, CITY DOESNT EXISTS", CONTAINS);

        assertThat(atms, is(notNullValue()));
        assertThat(atms.isEmpty(), is(true));
    }

    @Test
    public void whenSearchForAValidCityUsingContains() {
        String cityContains = "TIL";
        List<ATM> atms = atmService.searchATMs(cityContains, CONTAINS);

        assertThat(atms, is(notNullValue()));
        assertThat(atms.size(), is(greaterThan(0)));

        ATM firstObject = atms.get(0);

        assertThat(firstObject.getAddress().getCity(), containsString(cityContains));
    }

    @Test
    public void whenSearchForAValidCityUsingStartsWith() {
        String cityStartsWith = "ROT";
        List<ATM> atms = atmService.searchATMs(cityStartsWith, STARTS_WITH);

        assertThat(atms, is(notNullValue()));
        assertThat(atms.size(), is(greaterThan(0)));

        ATM firstObject = atms.get(0);

        assertThat(firstObject.getAddress().getCity(), startsWith(cityStartsWith));
    }

    @Test
    public void whenSearchForAValidCityUsingEndsWith() {
        String cityEndsWith = "TERDAM";
        List<ATM> atms = atmService.searchATMs(cityEndsWith, ENDS_WITH);

        assertThat(atms, is(notNullValue()));
        assertThat(atms.size(), is(greaterThan(0)));

        ATM firstObject = atms.get(0);

        assertThat(firstObject.getAddress().getCity(), endsWith(cityEndsWith));
    }

    @Test
    public void whenSearchForAValidCityUsingEquals() {
        String cityIs = "AMSTERDAM";
        List<ATM> atms = atmService.searchATMs(cityIs, EQUALS);

        assertThat(atms, is(notNullValue()));
        assertThat(atms.size(), is(greaterThan(0)));

        ATM firstObject = atms.get(0);

        assertThat(firstObject.getAddress().getCity(), equalToIgnoringCase(cityIs));
    }

}
