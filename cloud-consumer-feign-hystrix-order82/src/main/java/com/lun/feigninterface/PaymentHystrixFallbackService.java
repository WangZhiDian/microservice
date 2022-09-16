package com.lun.feigninterface;

import com.lun.common.bean.CommonResult;
import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixFallbackService implements PaymentHystrixService{
    @Override
    public CommonResult paymentInfo_OK(Integer id) {
        System.out.println("feign -on -fail");
        return new CommonResult(500, "feign-ok-fail", "-----PaymentFallbackService fall back-paymentInfo_OK ,o(╥﹏╥)o");
    }

    @Override
    public CommonResult paymentInfo_TimeOut(Integer id) {
        System.out.println("fein time fail");
        return new CommonResult(500, "feign-timeout-fail", "-----PaymentFallbackService fall back-paymentInfo_TimeOut ,o(╥﹏╥)o");
    }
}
