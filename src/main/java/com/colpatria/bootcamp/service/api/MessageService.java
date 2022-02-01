package com.colpatria.bootcamp.service.api;

import com.colpatria.bootcamp.dto.MessageDTO;

public interface MessageService {

    MessageDTO getMessageByValue(String value);
}
