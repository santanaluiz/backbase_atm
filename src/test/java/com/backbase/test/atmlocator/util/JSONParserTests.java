package com.backbase.test.atmlocator.util;

import com.backbase.test.atmlocator.AtmLocatorApplication;
import com.backbase.test.atmlocator.exceptions.ATMParseException;
import com.backbase.test.atmlocator.models.ATM;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by luizsantana on 10/6/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AtmLocatorApplication.class)
public class JSONParserTests {

    @Test(expected = ATMParseException.class)
    public void whenInvalidJsonObjectIsPassed() throws ATMParseException {
        JSONParser
                .init("{}}")
                .setEntityClass(ATM.class)
                .parseList();
    }

    @Test(expected = ATMParseException.class)
    public void whenJsonPassedIsNotAList() throws ATMParseException {
        JSONParser
                .init("{\"type\" : \"ING\"}")
                .setEntityClass(ATM.class)
                .parseList();
    }

    @Test
    public void whenJsonIsAValidJson() throws ATMParseException {
        List result = JSONParser
                .init("[{\"type\" : \"ING\"}]")
                .setEntityClass(ATM.class)
                .parseList();

        assertThat(result, is(notNullValue()));
        assertThat(result.isEmpty(), is(false));
        assertThat(result.size(), is(1));
    }

}
