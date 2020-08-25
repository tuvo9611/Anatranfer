package com.teso.AnaTransferserver.converter;

import com.teso.AnaTransferserver.bean.PaymentBean;
import com.teso.AnaTransferserver.bean.TransactionBean;
import com.teso.AnaTransferserver.model.Payment;
import com.teso.AnaTransferserver.model.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {
    public TransactionBean convertBean(Transaction transaction){
        ModelMapper modelMapper = new ModelMapper();
        TransactionBean transactionBean =  modelMapper.map(transaction,TransactionBean.class);
        return transactionBean;
    }

    public Transaction convertModel(TransactionBean transactionBean){
        ModelMapper modelMapper = new ModelMapper();
        Transaction transaction = modelMapper.map(transactionBean,Transaction.class);
        return transaction;
    }
}
