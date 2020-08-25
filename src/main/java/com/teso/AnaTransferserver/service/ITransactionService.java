package com.teso.AnaTransferserver.service;

import com.teso.AnaTransferserver.bean.StoreBean;
import com.teso.AnaTransferserver.bean.TransactionBean;
import com.teso.AnaTransferserver.model.Transaction;
import com.teso.AnaTransferserver.model.User;

import java.util.List;

public interface ITransactionService {
    public List<TransactionBean> findAll();
    public TransactionBean findById(Long id);
    public TransactionBean save(TransactionBean storeBean);

}
