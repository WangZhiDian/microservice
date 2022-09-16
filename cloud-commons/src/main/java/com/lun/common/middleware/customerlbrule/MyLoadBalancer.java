package com.lun.common.middleware.customerlbrule;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface MyLoadBalancer {

    ServiceInstance instances(List<ServiceInstance> serviceInstances);

}
