package com.colpatria.bootcamp.client;

import com.colpatria.bootcamp.dto.MyServiceResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

public interface MyServiceClient {

    @GetMapping(
            value = "${client.my-service.config.endpoint.health}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    MyServiceResponseDTO health();
}
