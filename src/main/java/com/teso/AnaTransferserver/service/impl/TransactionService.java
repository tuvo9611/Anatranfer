package com.teso.AnaTransferserver.service.impl;

import com.teso.AnaTransferserver.bean.StoreBean;
import com.teso.AnaTransferserver.bean.TransactionBean;
import com.teso.AnaTransferserver.converter.TransactionConverter;
import com.teso.AnaTransferserver.model.Store;
import com.teso.AnaTransferserver.model.Transaction;
import com.teso.AnaTransferserver.repository.ITransactionRepository;
import com.teso.AnaTransferserver.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private TransactionConverter transactionConverter;

    @Override
    public List<TransactionBean> findAll() {
        try {
            List<Transaction> transactions= transactionRepository.findAll();
            List<TransactionBean> results = transactions.stream()
                    .map(item -> transactionConverter.convertBean(item)).collect(Collectors.toList());
            return results;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public TransactionBean findById(Long id) {
        try{
            Transaction transaction = new Transaction();
            Optional<Transaction> transactionData = transactionRepository.findById(id);
            if(transactionData.isPresent()){
                transaction =  transactionData.get();
            }
            return transactionConverter.convertBean(transaction);
        }catch (Exception e){
            e.printStackTrace();
            return new TransactionBean();
        }
    }

    @Override
    public TransactionBean save(TransactionBean transactionBean) {
        try{
            Transaction transaction = transactionConverter.convertModel(transactionBean);
            transactionRepository.save(transaction);
            return transactionConverter.convertBean(transaction);
        }catch (Exception e){
            e.printStackTrace();
            return new TransactionBean();
        }
    }
}
