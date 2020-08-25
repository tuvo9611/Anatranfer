package com.teso.AnaTransferserver.service.impl;

import com.teso.AnaTransferserver.bean.BankBean;
import com.teso.AnaTransferserver.converter.BankConverter;
import com.teso.AnaTransferserver.model.Bank;
import com.teso.AnaTransferserver.repository.IBankRepository;
import com.teso.AnaTransferserver.service.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankService implements IBankService {

    @Autowired
    private IBankRepository bankRepository;

    @Autowired
    private BankConverter bankConverter;

    @Override
    public List<BankBean> findAll() {
        try {
            List<Bank> banks = bankRepository.findAll();
            List<BankBean> results = banks.stream()
                    .map(item -> bankConverter.convertBean(item)).collect(Collectors.toList());
            return results;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public BankBean findById(Long id) {
        try{
            Bank bank = new Bank();
            Optional<Bank> bankData = bankRepository.findById(id);
            if(bankData.isPresent()){
                bank = bankData.get();
            }
            return bankConverter.convertBean(bank);
        }catch (Exception e){
            e.printStackTrace();
            return new BankBean();
        }
    }

    @Override
    public void deleteById(Long id) {
        try{
            bankRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public BankBean save(BankBean bankBean) {
        try{
            Bank bank = bankConverter.convertModel(bankBean);
            bankRepository.save(bank);
            return bankConverter.convertBean(bank);
        }catch (Exception e){
            e.printStackTrace();
            return new BankBean();
        }
    }
}
