package com.colpatria.bootcamp.service;

import com.colpatria.bootcamp.entity.UserJpaEntity;
import com.colpatria.bootcamp.exception.ApiResponseCode;
import com.colpatria.bootcamp.exception.UserException;
import com.colpatria.bootcamp.repository.UserJpaRepository;
import com.colpatria.bootcamp.service.api.CacheService;
import com.colpatria.bootcamp.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;

    private final CacheService<UserJpaEntity> cacheService;

    public UserServiceImpl(UserJpaRepository userJpaRepository, CacheService<UserJpaEntity> cacheService) {

        this.userJpaRepository = userJpaRepository;
        this.cacheService = cacheService;
    }

    @Override
    public UserJpaEntity getUserByUsername(String username) {

        log.info("Query user on database by username: [{}]", username);
        return cacheService.get(username)
                .orElseGet(() ->  {
                    UserJpaEntity userJpaEntity = userJpaRepository.findByUsername(username)
                                    .orElseThrow(() -> new UserException("User by value not found", ApiResponseCode.USER_NOT_FOUND));
                    cacheService.put(username, userJpaEntity);
                    return userJpaEntity;
                });
    }
}
