package com.teso.AnaTransferserver.server.transactionService;

import com.teso.AnaTransferserver.enums.TransactionStatusCode;
import com.teso.AnaTransferserver.model.Transaction;
import com.teso.AnaTransferserver.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServersImp implements ITransactionServices {

    @Autowired
    ITransactionRepository transactionRepository;


    @Override
    @Transactional
    public Transaction findOne(Long transactionId) {
        return transactionRepository.findByTransactionId(transactionId);
    }

    @Override
    @Transactional
    public Transaction confirm(Long transactionId) {
        Transaction transaction = findOne(transactionId);
        transaction.setTransactionStatus(TransactionStatusCode.CONFIRM.getCode());
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    @Transactional
    public Transaction payment(Long transactionId) {
        Transaction transaction = findOne(transactionId);
        transaction.setTransactionStatus(TransactionStatusCode.PAYMENT.getCode());
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    @Transactional
    public Transaction complete(Long transactionId) {
        Transaction transaction = findOne(transactionId);
        transaction.setTransactionStatus(TransactionStatusCode.COMPLETE.getCode());
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    @Transactional
    public Transaction cancel(Long transactionId) {
        Transaction transaction = findOne(transactionId);
        transaction.setTransactionStatus(TransactionStatusCode.CANCELED.getCode());
        transactionRepository.save(transaction);
        return transaction;
    }
}
