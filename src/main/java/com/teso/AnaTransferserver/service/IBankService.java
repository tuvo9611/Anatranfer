package com.teso.AnaTransferserver.service;

import com.teso.AnaTransferserver.bean.BankBean;
import com.teso.AnaTransferserver.model.Bank;

import java.util.List;

public interface IBankService {
    public List<BankBean> findAll();
    public BankBean findById(Long id);
    public void deleteById(Long id);
    public BankBean save(BankBean bankBean);
}
