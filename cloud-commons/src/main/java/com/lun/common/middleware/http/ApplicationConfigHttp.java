package com.lun.common.middleware.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfigHttp {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


}
