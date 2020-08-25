package com.teso.AnaTransferserver.converter;

import com.teso.AnaTransferserver.bean.BankBean;
import com.teso.AnaTransferserver.bean.PaymentBean;
import com.teso.AnaTransferserver.bean.RecipientBean;
import com.teso.AnaTransferserver.model.Payment;
import com.teso.AnaTransferserver.model.Recipient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RecipientConverter {
//    public RecipientBean convertBean(Recipient recipient) {
//        ModelMapper modelMapper = new ModelMapper();
//        RecipientBean recipientBean = modelMapper.map(recipient, RecipientBean.class);
//        return recipientBean;
//    }
//    public Recipient convertModel (RecipientBean recipientBean){
//        ModelMapper modelMapper = new ModelMapper();
//        Recipient recipient = modelMapper.map(recipientBean,Recipient.class);
//        return recipient;
//
//    }
    public RecipientBean convertBean(Recipient recipient){
        ModelMapper modelMapper = new ModelMapper();
        RecipientBean recipientBean =  modelMapper.map(recipient,RecipientBean.class);
        return recipientBean;
    }

    public Recipient convertModel(RecipientBean recipientBean){
        ModelMapper modelMapper = new ModelMapper();
        Recipient recipient = modelMapper.map(recipientBean,Recipient.class);
        return recipient;
    }
}
