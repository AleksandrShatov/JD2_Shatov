package com.noirix.controller.rest;

import com.noirix.controller.requests.RoleCreateRequest;
import com.noirix.domain.hibernate.HibernateRole;
import com.noirix.repository.hibernate.HibernateRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hibernate/roles")
@RequiredArgsConstructor
public class HibernateRolesController {

    private final HibernateRoleRepository roleRepository;

    @GetMapping
    public List<HibernateRole> findAll() {
        System.out.println("In role rest controller");
        return roleRepository.findAll();
    }

    @GetMapping("/{id}")
    public HibernateRole findOne(@PathVariable Long id) {
        return roleRepository.findOne(id);
    }

    @PostMapping
    public HibernateRole save(@RequestBody RoleCreateRequest request) {

        HibernateRole role = new HibernateRole();
        role.setRoleName(request.getRoleName());

        return roleRepository.save(role);
    }

}
