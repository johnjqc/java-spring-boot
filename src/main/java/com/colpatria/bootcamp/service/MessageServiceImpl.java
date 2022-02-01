package com.colpatria.bootcamp.service;

import com.colpatria.bootcamp.dto.MessageDTO;
import com.colpatria.bootcamp.entity.MessageJpaEntity;
import com.colpatria.bootcamp.exception.MessageException;
import com.colpatria.bootcamp.repository.MessageJpaRepository;
import com.colpatria.bootcamp.service.api.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageJpaRepository messageJpaRepository;

    public MessageServiceImpl(MessageJpaRepository messageJpaRepository) {

        this.messageJpaRepository = messageJpaRepository;
    }

    @Override
    public MessageDTO getMessageByValue(String value) {

        log.info("Query message on database by value: [{}]", value);

        Optional<MessageJpaEntity> messageJpaEntity = messageJpaRepository.findByMessage(value);

        return messageJpaEntity
                .map(msg -> MessageDTO.builder()
                        .message(msg.getMessage())
                        .build())
                .orElseThrow(() -> new MessageException("Message by value not found"));
    }
}
