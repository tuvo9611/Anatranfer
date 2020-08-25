package com.teso.AnaTransferserver.repository;

import com.teso.AnaTransferserver.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends MongoRepository<User,Long> {
    User findByUserEmail(String email);

}
