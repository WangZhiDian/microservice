package com.lun.feigninterface;


import com.lun.common.bean.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",
                    fallback = PaymentHystrixFallbackService.class) // 指定降级类
public interface PaymentHystrixService {


    @GetMapping("/payment/hystrix/ok/{id}")
    public CommonResult paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public CommonResult paymentInfo_TimeOut(@PathVariable("id") Integer id);

}
