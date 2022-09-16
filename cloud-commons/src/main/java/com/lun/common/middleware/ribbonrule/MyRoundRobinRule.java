package com.lun.common.middleware.ribbonrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRoundRobinRule {

    @Bean
    public IRule roundRobinRule() {
        return new RoundRobinRule();
    }

}
