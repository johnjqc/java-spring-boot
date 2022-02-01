package com.colpatria.bootcamp.service.api;

import com.colpatria.bootcamp.exception.MyServiceException;
import com.colpatria.bootcamp.dto.MyServiceDTO;

public interface MyService {

    MyServiceDTO getHealth() throws MyServiceException;
}
