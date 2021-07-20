package com.noirix.repository.hibernate;


import com.noirix.domain.hibernate.HibernateLocation;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class LocationRepository implements HibernateLocationRepository {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<HibernateLocation> findAll() {
        try (Session session = sessionFactory.openSession()) {
//            return Collections.singletonList(session.find(HibernateLocation.class, 1L));
            return session.createQuery(
                    "select l from HibernateLocation l inner join l.dealers as d where d.id > 1",
                    HibernateLocation.class).getResultList();
        }
    }

    @Override
    public HibernateLocation findOne(Long id) {
        return null;
    }

    @Override
    public HibernateLocation save(HibernateLocation entity) {
        return null;
    }

    @Override
    public void addOne(HibernateLocation entity) {

    }

    @Override
    public void save(List<HibernateLocation> entities) {

    }

    @Override
    public HibernateLocation update(HibernateLocation entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Object strangeSearch() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "select minute(u.birthDate), upper(u.login), coalesce(u.password, 'sdfsdf') from HibernateUser u where u.password is null").list();
        }
    }
}
