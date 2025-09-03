package com.lcr.user.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    // creating bean for microservice
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
