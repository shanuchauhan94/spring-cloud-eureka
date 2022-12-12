package com.order.service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {

    @Bean
    @LoadBalanced // round robbin way to transfer request one by one
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
