package com.colpatria.bootcamp.service;

import com.colpatria.bootcamp.client.MyServiceClient;
import com.colpatria.bootcamp.dto.MyServiceDTO;
import com.colpatria.bootcamp.dto.MyServiceResponseDTO;
import com.colpatria.bootcamp.exception.MyServiceException;
import com.colpatria.bootcamp.service.api.MyService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.*;
import java.util.function.Supplier;

@Slf4j
@Service
public class MyServiceHealth implements MyService {

    private final MyServiceClient myServiceClient;

    public MyServiceHealth(MyServiceClient myServiceClient) {

        this.myServiceClient = myServiceClient;
    }

    @Override
    public MyServiceDTO getHealth() throws MyServiceException {

        log.info("Query health status to my service");

        Supplier<MyServiceResponseDTO> myServiceSupplier = myServiceClient::health;

        Callable<MyServiceResponseDTO> restrictedCall = TimeLimiter.decorateFutureSupplier(
                TimeLimiter.of(Duration.ofMillis(1000)), () -> CompletableFuture.supplyAsync(myServiceSupplier)
        );

        Callable<MyServiceResponseDTO> chainedCallable = CircuitBreaker.decorateCallable(
                CircuitBreaker.of("name-my-service",
                        CircuitBreakerConfig.custom().failureRateThreshold(30).build()
                ), restrictedCall
        );

        try {
            ExecutorService service = Executors.newSingleThreadExecutor();
            Future<MyServiceResponseDTO> result = service.submit(chainedCallable);
            MyServiceResponseDTO responseDTO =  result.get();

            return MyServiceDTO.builder()
                    .status(responseDTO.getStatus())
                    .build();
        } catch (InterruptedException | ExecutionException e) {
            throw new MyServiceException(e.getMessage());
        }
    }
}
