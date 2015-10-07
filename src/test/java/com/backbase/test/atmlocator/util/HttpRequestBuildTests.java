package com.backbase.test.atmlocator.util;

import com.backbase.test.atmlocator.AtmLocatorApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by luizsantana on 10/7/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AtmLocatorApplication.class)
public class HttpRequestBuildTests {

    public static final String TEST_URL = "https://api.github.com/users/backbase/repos";

    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidUrlIsUsed() {
        HttpRequestBuilder
                .init("invalid URL")
                .request();
    }

    @Test
    public void whenHasAValidUrl() {
        String responseBody = HttpRequestBuilder
                .init(TEST_URL)
                .request();
        
        assertThat(responseBody, is(notNullValue()));
    }
}
