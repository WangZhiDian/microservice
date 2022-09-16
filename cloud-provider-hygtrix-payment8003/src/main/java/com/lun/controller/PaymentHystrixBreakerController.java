package com.lun.controller;

import com.lun.common.bean.CommonResult;
import com.lun.service.PaymentBreakService;
import com.lun.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentHystrixBreakerController {

    @Autowired
    private PaymentBreakService paymentBreakService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/circuit/{id}")
    CommonResult paymentCircuit(@PathVariable("id") Integer id)
    {
        String result = paymentBreakService.paymentCircuitBreaker(id);
        System.out.println("*****result: "+result);
        return new CommonResult(200, "success", result);
    }


}
