package com.teso.AnaTransferserver.converter;

import com.teso.AnaTransferserver.bean.BankBean;
import com.teso.AnaTransferserver.model.Bank;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BankConverter {
    public BankBean convertBean(Bank bank){
        ModelMapper modelMapper = new ModelMapper();
        BankBean bankBean =  modelMapper.map(bank,BankBean.class);
        return bankBean;
    }

    public Bank convertModel(BankBean bankBean){
        ModelMapper modelMapper = new ModelMapper();
        Bank bank = modelMapper.map(bankBean,Bank.class);
        return bank;
    }
}
