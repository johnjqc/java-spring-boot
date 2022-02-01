package com.colpatria.bootcamp.service.api;

import java.util.Optional;

public interface CacheService<T> {

    boolean put(String key, T object);

    Optional<T> get(String key);
}
