package com.lun.controller;

import com.lun.common.bean.CommonResult;
import com.lun.feigninterface.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystirxGlobalController {


    @Autowired
    PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/global/ok/{id}")
    public CommonResult paymentInfo_OK(@PathVariable("id") Integer id)
    {
        return paymentHystrixService.paymentInfo_OK(id);
    }

    // 通过全局但降级方法，单独降级
    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/global/timeout/{id}")
    public CommonResult paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }

    //定义一个全局但响应
    public CommonResult payment_Global_FallbackMethod(){
        return new CommonResult(400, "global fail", "我是消费者80, 对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o");
    }

}
