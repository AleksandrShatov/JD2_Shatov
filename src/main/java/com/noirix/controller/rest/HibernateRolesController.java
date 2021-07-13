package com.noirix.controller.rest;

import com.noirix.domain.hibernate.HibernateRoles;
import com.noirix.repository.hibernate.HibernateRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
