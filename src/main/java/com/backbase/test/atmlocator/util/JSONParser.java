package com.backbase.test.atmlocator.util;

import com.backbase.test.atmlocator.exceptions.ATMParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

/**
 * Created by luizsantana on 10/6/15.
 */
public class JSONParser {
    public JSONParser() {
        throw new UnsupportedOperationException("You must call method init(jsonResponse)");
    }

    public static Builder init(String jsonResponse) {
        return new Builder(jsonResponse);
    }

    public static class Builder {
        private final String jsonResponse;
        private final ObjectMapper objectMapper;
        private Class entityClass;
        private RequestCacheManager cacheManager;

        public Builder(String jsonResponse) {
            this.jsonResponse = jsonResponse;
            this.objectMapper = new ObjectMapper();
            this.cacheManager = RequestCacheManager.getInstance();
        }

        public Builder setEntityClass(Class entityClass) {
            this.entityClass = entityClass;
            return this;
        }

        public List parseList() throws ATMParseException {
            try {
                if (cacheManager.isObjectInCache(jsonResponse)) {
                    return (List) cacheManager.getObjectCache(jsonResponse);
                }

                List parsedObject = objectMapper.readValue(jsonResponse, getValueType());
                cacheManager.saveObjectAsCache(jsonResponse, parsedObject);
                return parsedObject;
            } catch (Exception exception) {
                exception.printStackTrace();
                throw new ATMParseException("Error while trying to parse a JSON Response to a List");
            }
        }

        private CollectionType getValueType() {
            return objectMapper.getTypeFactory().constructCollectionType(List.class, entityClass);
        }
    }
}
