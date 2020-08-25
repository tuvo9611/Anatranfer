package com.teso.AnaTransferserver.server;

import com.teso.AnaTransferserver.model.Role;
import com.teso.AnaTransferserver.repository.IRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBSeeder implements CommandLineRunner {


    private IRoleRepository roleRepository;

    public DBSeeder(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        Role admin = roleRepository.findByRole("ADMIN");
//        if(admin == null){
//            Role newAdminRole = new Role();
//            newAdminRole.setRoleId(1L);
//            newAdminRole.setRole("ADMIN");
//            roleRepository.save(newAdminRole);
//        }
//
//        Role user = roleRepository.findByRole("USER");
//        if(user == null){
//            Role newUserRole = new Role();
//            newUserRole.setRoleId(2L);
//            newUserRole.setRole("USER");
//            roleRepository.save(newUserRole);
//        }
//        Role cashier = roleRepository.findByRole("cashier");
//        if(cashier == null){
//            Role newCashierRole = new Role();
//            newCashierRole.setRoleId(3L);
//            newCashierRole.setRole("cashier");
//            roleRepository.save(newCashierRole);
//       }
    }
}
