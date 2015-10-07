package com.backbase.test.atmlocator.util;

import com.backbase.test.atmlocator.exceptions.CacheNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luizsantana on 10/6/15.
 */
@Component
public class RequestCacheManager {
    private static RequestCacheManager instance;
    private static Map<Integer, Object> cache = new HashMap<>();

    public static RequestCacheManager getInstance() {
        if (instance == null) {
            instance = new RequestCacheManager();
        }

        return instance;
    }

    public boolean isObjectInCache(String requestJson) {
        return cache.containsKey(requestJson.hashCode());
    }

    public Object getObjectCache(String requestJson) throws CacheNotFoundException {
        if (isObjectInCache(requestJson)) {
            return cache.get(requestJson.hashCode());
        }

        throw new CacheNotFoundException("There is no cached object with for this request");
    }

    public void saveObjectAsCache(String requestJson, Object parsedValue) {
        cache.put(requestJson.hashCode(), parsedValue);
    }
}
