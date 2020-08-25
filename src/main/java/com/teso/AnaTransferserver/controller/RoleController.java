package com.teso.AnaTransferserver.controller;

import com.teso.AnaTransferserver.model.Role;
import com.teso.AnaTransferserver.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin
@RequestMapping("/roles")
@RestController
public class RoleController {
    @Autowired
    IRoleRepository roleRepository;

    @GetMapping("/listRole")
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @GetMapping("/detailRole/{roleId}")
    public Optional<Role> getRoleById(@PathVariable Long roleId) {
        return roleRepository.findById(roleId);
    }

    @DeleteMapping("/deleteRole/{roleId}")
    public Long deleteRoleById(@PathVariable Long roleId) {
        roleRepository.deleteById(roleId);
        return roleId;
    }

    @PutMapping("/Role")
    public Role createRole(@RequestBody Role role) {
        roleRepository.save(role);
        return role;
    }

    @PostMapping("/editRole")
    public Role updateRole(@RequestBody Role role) {
        roleRepository.save(role);
        return role;
    }

}
