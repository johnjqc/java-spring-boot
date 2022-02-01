package com.colpatria.bootcamp.service;

import com.colpatria.bootcamp.entity.UserJpaEntity;
import com.colpatria.bootcamp.service.api.CacheService;
import com.colpatria.bootcamp.util.CacheStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserCacheService implements CacheService<UserJpaEntity> {

    private final CacheStore<UserJpaEntity> userCacheStore = new CacheStore<>(120, TimeUnit.SECONDS);

    @Override
    public boolean put(String key, UserJpaEntity messageDTO) {

        log.info("Put record on cache service with key: [{}]", key);
        return userCacheStore.add(key, messageDTO);
    }

    @Override
    public Optional<UserJpaEntity> get(String key) {

        log.info("Getting user from cache service with key: [{}]", key);
        return userCacheStore.get(key);
    }

}
