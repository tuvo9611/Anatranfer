package com.teso.AnaTransferserver.repository;

import com.teso.AnaTransferserver.model.Bank;
import com.teso.AnaTransferserver.model.Rate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRateRepository extends MongoRepository<Rate,Long> {
}
