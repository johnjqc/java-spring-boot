package com.colpatria.bootcamp.service;

import com.colpatria.bootcamp.dto.MessageDTO;
import com.colpatria.bootcamp.service.api.CacheService;
import com.colpatria.bootcamp.service.api.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageServiceProxy implements MessageService {

    private final MessageService messageService;

    private final CacheService<MessageDTO> cacheService;

    public MessageServiceProxy(@Qualifier("messageServiceImpl") MessageService messageService,
                               @Qualifier("messageCacheService") CacheService<MessageDTO> cacheService) {

        this.messageService = messageService;
        this.cacheService = cacheService;
    }

    @Override
    public MessageDTO getMessageByValue(String value) {

        log.info("Query message by proxy: [{}]", value);
        return cacheService.get(value)
                .orElseGet(() -> getMessageByValueFromService(value));
    }

    private MessageDTO getMessageByValueFromService(String value) {

        log.info("Message not found in cache, query by service: [{}]", value);

        MessageDTO messageDTO = messageService.getMessageByValue(value);
        cacheService.put("", messageDTO);

        return messageDTO;
    }
}
