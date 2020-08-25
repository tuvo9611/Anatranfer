package com.teso.AnaTransferserver.repository;

import com.teso.AnaTransferserver.model.PromoteCode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPromoteCodeRepository extends MongoRepository<PromoteCode,Long> {
}
