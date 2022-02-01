package com.colpatria.bootcamp.controller;

import com.colpatria.bootcamp.controller.dto.ResponseHealth;
import com.colpatria.bootcamp.dto.MyServiceDTO;
import com.colpatria.bootcamp.exception.ApiResponseCode;
import com.colpatria.bootcamp.exception.MyServiceException;
import com.colpatria.bootcamp.exception.UserException;
import com.colpatria.bootcamp.service.api.MyService;
import com.colpatria.digitalfactory.api.dto.response.ApiResponseDF;
import com.colpatria.service.HealthApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HealthController implements HealthApi {

    private final MyService myService;

    public HealthController(MyService myService) {

        this.myService = myService;
    }

    @Override
    public ResponseEntity<ApiResponseDF<ResponseHealth>> health() {

        log.info("Request health to my service");

        try {
            MyServiceDTO myServiceDTO = myService.getHealth();

            ResponseHealth responseHealth = new ResponseHealth();
            responseHealth.setStatus(myServiceDTO.getStatus());

            ApiResponseDF<ResponseHealth> apiResponseDF = new ApiResponseDF<>(responseHealth, null);

            return ResponseEntity.ok(apiResponseDF);
        } catch (MyServiceException e) {
            throw new UserException(e.getMessage(), ApiResponseCode.ERROR);
        }
    }
}
