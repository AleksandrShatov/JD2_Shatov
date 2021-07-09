package com.noirix.repository.impl;

import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.repository.HibernateUserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HibernateRepository implements HibernateUserRepository {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;


    @Override
    public List<HibernateUser> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return Collections.singletonList(session.find(HibernateUser.class, 622326L));
        }
    }

    @Override
    public HibernateUser findOne(Long id) {
        return null;
    }

    @Override
    public HibernateUser save(HibernateUser entity) {
        return null;
    }

    @Override
    public void addOne(HibernateUser entity) {

    }

    @Override
    public void save(List<HibernateUser> entities) {

    }

    @Override
    public HibernateUser update(HibernateUser entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
