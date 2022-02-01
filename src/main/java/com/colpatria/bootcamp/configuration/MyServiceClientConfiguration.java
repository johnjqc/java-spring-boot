package com.colpatria.bootcamp.configuration;


import com.colpatria.bootcamp.client.MyServiceClient;
import feign.Contract;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({FeignClientsConfiguration.class})
public class MyServiceClientConfiguration {

    @Bean
    public MyServiceClient myServiceClient(Encoder encoder, Decoder decoder, Contract contract,
                                           ClientMyServiceProperties clientMyServiceProperties) {

        return getClient(decoder, encoder, contract, MyServiceClient.class,
                clientMyServiceProperties.getConfig().getUrl());
    }

    private <T> T getClient(Decoder decoder, Encoder encoder, Contract contract, Class<T> clazz, String baseUrl ) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        return Feign.builder()
                .client(new feign.okhttp.OkHttpClient(okHttpClient))
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .target(clazz, baseUrl);
    }

}
