package com.backbase.test.atmlocator.controller;

import com.backbase.test.atmlocator.models.ATM;
import com.backbase.test.atmlocator.services.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by luizsantana on 10/6/15.
 */
@RestController
@RequestMapping("/atm")
public class ATMLocatorController {

    @Autowired
    private ATMService atmService;

    @RequestMapping(value = "/search/{city}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ATM> search(@PathVariable("city") String city) {
        return atmService.searchATMs(city, ATMService.SearchType.CONTAINS);
    }
}
