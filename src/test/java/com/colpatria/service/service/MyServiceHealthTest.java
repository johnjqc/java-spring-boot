package com.colpatria.service.service;

import com.colpatria.bootcamp.client.MyServiceClient;
import com.colpatria.bootcamp.dto.MyServiceDTO;
import com.colpatria.bootcamp.dto.MyServiceResponseDTO;
import com.colpatria.bootcamp.exception.MyServiceException;
import com.colpatria.bootcamp.service.MyServiceHealth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MyServiceHealthTest {

    @Mock
    private MyServiceClient myServiceClient;

    private MyServiceHealth myServiceHealth;

    @BeforeEach
    public void setUp() {
        myServiceHealth = new MyServiceHealth(myServiceClient);
    }

    @Test
    public void getHealthTest() throws MyServiceException {

        when(myServiceClient.health()).thenReturn(new MyServiceResponseDTO());

        MyServiceDTO responseDTO = myServiceHealth.getHealth();

        assertThat(responseDTO, is(notNullValue()));
    }
}
