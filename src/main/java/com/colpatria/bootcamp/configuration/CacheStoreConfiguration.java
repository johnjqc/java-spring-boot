package com.colpatria.bootcamp.configuration;

import com.colpatria.bootcamp.dto.MessageDTO;
import com.colpatria.bootcamp.util.CacheStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheStoreConfiguration {

    @Value("${cache.message.expirationTime:120}")
    private Integer messageExpirationTime;


    @Bean
    public CacheStore<MessageDTO> messageCache() {

        return new CacheStore<>(messageExpirationTime, TimeUnit.SECONDS);
    }
}
