package com.noirix.repository.hibernate;

import com.noirix.domain.hibernate.HibernateRoles;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoleRepository implements HibernateRoleRepository {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Autowired
    @Qualifier("entityManagerFactory")
    private EntityManager entityManager;

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
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long roleId = (Long) session.save(entity);
            transaction.commit();
            return findOne(roleId);
        }

//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        entityManager.persist(entity);
//        transaction.commit();
//        return entityManager.find(HibernateRoles.class, entity.getId());
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
