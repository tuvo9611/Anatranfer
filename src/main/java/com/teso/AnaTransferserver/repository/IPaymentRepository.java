package com.teso.AnaTransferserver.repository;

import com.teso.AnaTransferserver.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentRepository extends MongoRepository<Payment,Long> {
}
