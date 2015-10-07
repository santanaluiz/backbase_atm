package com.backbase.test.atmlocator.util;

import org.springframework.web.client.RestTemplate;

/**
 * Created by luizsantana on 10/6/15.
 */
public class HttpRequestBuilder {

    public HttpRequestBuilder() {
        throw new UnsupportedOperationException("You must call method init(URL)");
    }

    public static Builder init(String requestUrl) {
        return new Builder(requestUrl);
    }

    public static class Builder {
        public static final String ING_REQUEST_PREFIX = ")]}',";
        private String url;

        public Builder(String url) {
            this.url = url;
        }

        public String request() {
            return makeRequestAndCleanUp();
        }

        private String makeRequestAndCleanUp() {
            String responseJson = getResponseBody();
            responseJson = cleanUpJson(responseJson);
            return responseJson;
        }

        private String cleanUpJson(String responseJson) {
            if (isAValidIngResponse(responseJson)) {
                return responseJson.substring(ING_REQUEST_PREFIX.length());
            }
            return responseJson;
        }

        private String getResponseBody() {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(url, String.class);
        }

        private boolean isAValidIngResponse(String responseJson) {
            return responseJson.startsWith(ING_REQUEST_PREFIX);
        }
    }
}
