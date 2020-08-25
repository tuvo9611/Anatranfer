package com.teso.AnaTransferserver.repository;

import com.teso.AnaTransferserver.model.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBankRepository extends MongoRepository<Bank,Long> {

}
