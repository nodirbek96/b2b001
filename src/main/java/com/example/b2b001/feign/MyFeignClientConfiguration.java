package com.example.b2b001.feign;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class MyFeignClientConfiguration {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}
