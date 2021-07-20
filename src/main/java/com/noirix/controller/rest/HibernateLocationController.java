package com.noirix.controller.rest;

import com.noirix.domain.hibernate.HibernateLocation;
import com.noirix.repository.hibernate.HibernateLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hibernate/location")
@RequiredArgsConstructor
public class HibernateLocationController {

    private final HibernateLocationRepository locationRepository;

    @GetMapping
    public List<HibernateLocation> findAll() {
        System.out.println("In location rest controller");
        return locationRepository.findAll();
    }

    @GetMapping("/search")
    public Object search() {
        System.out.println("In location rest controller");
        return locationRepository.strangeSearch();
    }
}
