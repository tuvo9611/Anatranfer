package com.teso.AnaTransferserver.server.transactionService;

import com.teso.AnaTransferserver.model.Transaction;

public interface ITransactionServices {

    Transaction findOne(Long transactionId);

    Transaction confirm(Long transactionId);

    Transaction payment(Long transactionId);

    Transaction complete(Long transactionId);

    Transaction cancel(Long transactionId);
}
