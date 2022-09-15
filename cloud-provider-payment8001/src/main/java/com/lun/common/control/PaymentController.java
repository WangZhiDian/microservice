package com.lun.common.control;


import com.lun.common.bean.CommonResult;
import com.lun.common.entity.Payment;
import com.lun.common.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    private int serverPort = 8081;

    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment) {

        int result = paymentService.create(payment);
//        log.info("*****插入结果："+result);
        System.out.println("*****插入结果："+result);

        if(result > 0)
            return new CommonResult(200, "插入数据库成功,serverPort: " + serverPort, result);
        else{
            return new CommonResult(444, "插入数据库失败", null);
        }


        /*CommonResult result = new CommonResult();
        return result;*/
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


}
