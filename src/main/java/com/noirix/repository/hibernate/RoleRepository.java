package com.noirix.repository.hibernate;

import com.noirix.domain.hibernate.HibernateRole;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
    public List<HibernateRole> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return Collections.singletonList(session.find(HibernateRole.class, 1L));
        }
    }

    @Override
    public HibernateRole findOne(Long id) {
        try (Session session = sessionFactory.openSession()) {

            Query<HibernateRole> query = session.createQuery("select r from HibernateRole r where r.id = :id", HibernateRole.class);
            query.setParameter("id", id);

            return query.getSingleResult();
        }
    }

    @Override
    public HibernateRole save(HibernateRole entity) {
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
//        return entityManager.find(HibernateRole.class, entity.getId());
    }

    @Override
    public void addOne(HibernateRole entity) {

    }

    @Override
    public void save(List<HibernateRole> entities) {

    }

    @Override
    public HibernateRole update(HibernateRole entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
