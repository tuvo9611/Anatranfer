package com.teso.AnaTransferserver.repository;

import com.teso.AnaTransferserver.model.Recipient;
import com.teso.AnaTransferserver.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface IRecipientRepository extends MongoRepository<Recipient,Long> {
}
