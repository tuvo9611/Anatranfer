package com.teso.AnaTransferserver.service;

import com.teso.AnaTransferserver.bean.PaymentBean;

import java.util.List;

public interface IPaymentService {
    public List<PaymentBean> findAll();
    public PaymentBean findById(Long id);
    public void deleteById(Long id);
    public PaymentBean save(PaymentBean paymentBean);
}
