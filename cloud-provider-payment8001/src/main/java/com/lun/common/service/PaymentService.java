package com.lun.common.service;


import com.lun.common.entity.Payment;

public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(Long id);

}
