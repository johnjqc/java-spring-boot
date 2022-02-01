package com.colpatria.bootcamp.service;

import com.colpatria.bootcamp.dto.MessageDTO;
import com.colpatria.bootcamp.service.api.CacheService;
import com.colpatria.bootcamp.util.CacheStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MessageCacheService implements CacheService<MessageDTO> {

    private final CacheStore<MessageDTO> messageCacheStore;

    public MessageCacheService(CacheStore<MessageDTO> messageCacheStore) {

        this.messageCacheStore = messageCacheStore;
    }

    @Override
    public boolean put(String key, MessageDTO messageDTO) {

        log.info("Put message on cache service: [{}]", messageDTO.getMessage());

        return messageCacheStore.add("", messageDTO);
    }

    @Override
    public Optional<MessageDTO> get(String value) {

        log.info("Getting message from cache service: [{}]", value);
        return messageCacheStore.get(value);
    }
}
