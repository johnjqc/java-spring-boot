package com.colpatria.service.service;

import com.colpatria.bootcamp.entity.UserJpaEntity;
import com.colpatria.bootcamp.service.UserCacheService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class UserCacheServiceTest {

    private UserCacheService userCacheService;

    @BeforeEach
    public void setUp() {

        userCacheService = new UserCacheService();
    }

    @Test
    public void testPutMethod() {

        boolean result = userCacheService.put("anyKey", new UserJpaEntity());

        assertTrue(result);
    }

    @Test
    public void testGetMethod() {

        Optional<UserJpaEntity> result = userCacheService.get("anyKey");
        assertFalse(result.isPresent());
    }
}
