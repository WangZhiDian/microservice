package com.lun.control;


import com.lun.common.bean.CommonResult;
import com.lun.common.entity.Payment;
import com.lun.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port:8082}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {

        int result = paymentService.create(payment);
//        log.info("*****插入结果："+result);
        System.out.println("*****插入结果："+result);

        if(result > 0)
            return new CommonResult(200, "插入数据库成功,serverPort: " + serverPort, result);
        else{
            return new CommonResult(444, "插入数据库失败");
        }

    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
    {
        Payment payment = paymentService.getPaymentById(id);

        if(payment != null)
        {
            return new CommonResult(200,"查询成功,serverPort:  " + serverPort, payment);
        }else{
            return new CommonResult(444,"没有对应记录,查询ID: " + id,null);
        }
    }

    // 该方法为了验证Feign的超时设置
    @GetMapping(value = "/payment/timeout/get/{id}")
    public CommonResult<Payment> getPaymentTimeOutById(@PathVariable("id") Long id)
    {
        Payment payment = paymentService.getPaymentById(id);

        try{
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        if(payment != null)
        {
            return new CommonResult(200,"查询成功,serverPort:  " + serverPort, payment);
        }else{
            return new CommonResult(444,"没有对应记录,查询ID: " + id,null);
        }
    }

}
