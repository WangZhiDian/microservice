package com.lun.openfeign;

import com.lun.common.bean.CommonResult;
import com.lun.common.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {


    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);


    // 该方法为了验证Feign的超时设置,feign默认超时时间为1秒
    @GetMapping(value = "/payment/timeout/get/{id}")
    public CommonResult<Payment> getPaymentTimeOutById(@PathVariable("id") Long id);

}
