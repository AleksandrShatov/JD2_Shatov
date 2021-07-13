package com.noirix.repository.hibernate;

import com.noirix.domain.hibernate.HibernateRoles;
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
public class RoleRepository implements HibernateRoleRepository {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<HibernateRoles> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return Collections.singletonList(session.find(HibernateRoles.class, 1L));
        }
    }

    @Override
    public HibernateRoles findOne(Long id) {
        return null;
    }

    @Override
    public HibernateRoles save(HibernateRoles entity) {
        return null;
    }

    @Override
    public void addOne(HibernateRoles entity) {

    }

    @Override
    public void save(List<HibernateRoles> entities) {

    }

    @Override
    public HibernateRoles update(HibernateRoles entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
