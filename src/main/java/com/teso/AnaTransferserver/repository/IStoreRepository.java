package com.teso.AnaTransferserver.repository;

import com.teso.AnaTransferserver.model.Store;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStoreRepository extends MongoRepository<Store,Long> {
}
