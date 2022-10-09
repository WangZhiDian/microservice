package com.lun.controller;

import com.lun.domain.CommonResult;
import com.lun.domain.Order;
import com.lun.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {


    @Autowired
    OrderService orderService;

    @PostMapping("/seata/order/create")
    public CommonResult create(@RequestBody Order order) {

        orderService.create(order);

        return new CommonResult(200, "创建订单成功");

    }

}
