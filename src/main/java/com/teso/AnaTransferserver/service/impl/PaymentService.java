package com.teso.AnaTransferserver.service.impl;

import com.teso.AnaTransferserver.bean.BankBean;
import com.teso.AnaTransferserver.bean.PaymentBean;
import com.teso.AnaTransferserver.converter.PaymentConverter;
import com.teso.AnaTransferserver.model.Bank;
import com.teso.AnaTransferserver.model.Payment;
import com.teso.AnaTransferserver.repository.IPaymentRepository;
import com.teso.AnaTransferserver.service.IBankService;
import com.teso.AnaTransferserver.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private IPaymentRepository paymentRepository;

    @Autowired
    private PaymentConverter paymentConverter;

    @Override
    public List<PaymentBean> findAll() {
        try {
            List<Payment> payments = paymentRepository.findAll();
            List<PaymentBean> results = payments.stream()
                    .map(item -> paymentConverter.convertBean(item)).collect(Collectors.toList());
            return results;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public PaymentBean findById(Long id) {
        try{
            Payment payment = new Payment();
            Optional<Payment> paymentData = paymentRepository.findById(id);
            if(paymentData.isPresent()){
                payment = paymentData.get();
            }
            return paymentConverter.convertBean(payment);
        }catch (Exception e){
            e.printStackTrace();
            return new PaymentBean();
        }
    }

    @Override
    public void deleteById(Long id) {
        try{
            paymentRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public PaymentBean save(PaymentBean paymentBean) {
        try{
            Payment payment = paymentConverter.convertModel(paymentBean);
            paymentRepository.save(payment);
            return paymentConverter.convertBean(payment);
        }catch (Exception e){
            e.printStackTrace();
            return new PaymentBean();
        }
    }
}
