package com.noirix.controller.rest;

import com.noirix.controller.requests.SearchRequest;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.repository.hibernate.HibernateUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hibernate/user")
@RequiredArgsConstructor
public class HibernateUserController {

    private final HibernateUserRepository hibernateUserRepository;

    @GetMapping
    public List<HibernateUser> findAll() {
        System.out.println("In hibernate rest controller");
        return hibernateUserRepository.findAll();
    }

    @GetMapping("/search")
    public List<HibernateUser> search(@ModelAttribute SearchRequest request) {
        return hibernateUserRepository.criteriaApiSearch();
    }
}
