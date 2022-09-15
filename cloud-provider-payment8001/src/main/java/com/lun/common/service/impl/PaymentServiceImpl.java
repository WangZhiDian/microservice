package com.lun.common.service.impl;


import com.lun.common.dao.PaymentDao;
import com.lun.common.entity.Payment;
import com.lun.common.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {


    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
//        return paymentDao.create(payment);
        return 1;
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
//        return null;
    }
}
