package com.colpatria.bootcamp.service.api;

import com.colpatria.bootcamp.entity.UserJpaEntity;

public interface UserService {

    UserJpaEntity getUserByUsername(String username);
}
