package com.colpatria.bootcamp.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CacheStore<T> {

    private Cache<String, T> cache;

    public CacheStore(int expiryDuration, TimeUnit timeUnit) {

        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(expiryDuration, timeUnit)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();
    }

    public Optional<T> get(String key) {
        return Optional.ofNullable(cache.getIfPresent(key));
    }

    public boolean add(String key, T value) {
        if (key != null && value != null) {
            cache.put(key, value);
            log.info("Record stored with key [{}]", key);
            return true;
        }
        return false;
    }
}
