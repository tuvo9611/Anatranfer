package com.teso.AnaTransferserver.repository;

import com.teso.AnaTransferserver.model.Transaction;
import com.teso.AnaTransferserver.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransactionRepository extends MongoRepository<Transaction,Long> {

    List<Transaction> findByUserSend(User user);

    Transaction findByTransactionId(Long id);
}
