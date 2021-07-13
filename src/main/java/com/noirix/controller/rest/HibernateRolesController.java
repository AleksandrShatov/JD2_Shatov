package com.noirix.controller.rest;

import com.noirix.controller.requests.RoleCreateRequest;
import com.noirix.domain.hibernate.HibernateRoles;
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
    public List<HibernateRoles> findAll() {
        System.out.println("In role rest controller");
        return roleRepository.findAll();
    }

    @PostMapping
    public HibernateRoles save(@RequestBody RoleCreateRequest request) {

        HibernateRoles role = new HibernateRoles();
        role.setRoleName(request.getRoleName());

        return roleRepository.save(role);
    }

}
