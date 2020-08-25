package com.teso.AnaTransferserver.converter;

import com.teso.AnaTransferserver.bean.PaymentBean;
import com.teso.AnaTransferserver.bean.PromoteCodeBean;
import com.teso.AnaTransferserver.bean.RateBean;
import com.teso.AnaTransferserver.model.Payment;
import com.teso.AnaTransferserver.model.PromoteCode;
import com.teso.AnaTransferserver.model.Rate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RateConverter {
    public RateBean convertBean(Rate rate){
        ModelMapper modelMapper = new ModelMapper();
        RateBean rateBean =  modelMapper.map(rate,RateBean.class);
        return rateBean;
    }

    public Rate convertModel(RateBean rateBean){
        ModelMapper modelMapper = new ModelMapper();
        Rate rate = modelMapper.map(rateBean,Rate.class);
        return rate;
    }
}
