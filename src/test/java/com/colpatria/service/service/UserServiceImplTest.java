package com.colpatria.service.service;

import com.colpatria.bootcamp.entity.UserJpaEntity;
import com.colpatria.bootcamp.repository.UserJpaRepository;
import com.colpatria.bootcamp.service.UserServiceImpl;
import com.colpatria.bootcamp.service.api.CacheService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserJpaRepository userJpaRepository;

    @Mock
    private CacheService<UserJpaEntity> cacheService;

    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userJpaRepository, cacheService);
    }

    @Test
    public void getUserByUsernameTest() {

        when(cacheService.get(anyString())).thenReturn(Optional.of(new UserJpaEntity()));

        UserJpaEntity userJpaEntity = userService.getUserByUsername("user");

        assertThat(userJpaEntity, is(notNullValue()));
    }

    @Test
    public void getUserByUsernameFromDBTest() {

        when(cacheService.get(anyString())).thenReturn(Optional.empty());
        when(userJpaRepository.findByUsername(anyString())).thenReturn(Optional.of(new UserJpaEntity()));

        UserJpaEntity userJpaEntity = userService.getUserByUsername("user");

        assertThat(userJpaEntity, is(notNullValue()));
    }
}
