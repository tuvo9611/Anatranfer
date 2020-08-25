package com.teso.AnaTransferserver.converter;

import com.teso.AnaTransferserver.bean.BankBean;
import com.teso.AnaTransferserver.bean.PaymentBean;
import com.teso.AnaTransferserver.model.Bank;
import com.teso.AnaTransferserver.model.Payment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverter {
    public PaymentBean convertBean(Payment payment){
        ModelMapper modelMapper = new ModelMapper();
        PaymentBean paymentBean =  modelMapper.map(payment,PaymentBean.class);
        return paymentBean;
    }

    public Payment convertModel(PaymentBean paymentBean){
        ModelMapper modelMapper = new ModelMapper();
        Payment payment = modelMapper.map(paymentBean,Payment.class);
        return payment;
    }
}
