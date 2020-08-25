package com.teso.AnaTransferserver.repository;

import com.teso.AnaTransferserver.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends MongoRepository<Role,Long> {

    Role findByRole(String role);

    Role findByRoleId(Long id);
}
