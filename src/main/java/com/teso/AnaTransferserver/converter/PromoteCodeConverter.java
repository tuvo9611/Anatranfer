package com.teso.AnaTransferserver.converter;

import com.teso.AnaTransferserver.bean.PaymentBean;
import com.teso.AnaTransferserver.bean.PromoteCodeBean;
import com.teso.AnaTransferserver.model.Payment;
import com.teso.AnaTransferserver.model.PromoteCode;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PromoteCodeConverter {
    public PromoteCodeBean convertBean(PromoteCode promoteCode){
        ModelMapper modelMapper = new ModelMapper();
        PromoteCodeBean promoteCodeBean =  modelMapper.map(promoteCode,PromoteCodeBean.class);
        return promoteCodeBean;
    }

    public PromoteCode convertModel(PromoteCodeBean promoteCodeBean){
        ModelMapper modelMapper = new ModelMapper();
        PromoteCode promoteCode = modelMapper.map(promoteCodeBean,PromoteCode.class);
        return promoteCode;
    }
}
