package com.colpatria.bootcamp.controller;

import com.colpatria.bootcamp.controller.dto.ResponseUser;
import com.colpatria.bootcamp.service.api.UserService;
import com.colpatria.digitalfactory.api.dto.response.ApiResponseDF;
import com.colpatria.service.UserApi;
import com.colpatria.bootcamp.entity.UserJpaEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController implements UserApi {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @Override
    public ResponseEntity<ApiResponseDF<ResponseUser>> userGetByUsername(String userName) {
        
        log.info("Request user by username");

        UserJpaEntity userDTO = userService.getUserByUsername(userName);

        ResponseUser responseUser = new ResponseUser();
        responseUser.setUsername(userDTO.getUsername());
        responseUser.setStatus(userDTO.getStatus());

        ApiResponseDF<ResponseUser> apiResponseDF = new ApiResponseDF<>(responseUser, null);

        return ResponseEntity.ok(apiResponseDF);
    }
}
