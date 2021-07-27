package com.noirix.repository.hibernate;

import com.noirix.controller.requests.SearchRequest;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.domain.hibernate.HibernateUser_;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HibernateRepository implements HibernateUserRepository {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Autowired
    @Qualifier("entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public List<HibernateUser> findAll() {
        try (Session session = sessionFactory.openSession()) {
//            return Collections.singletonList(session.find(HibernateUser.class, 622432L));
            Query<HibernateUser> query = session.createNamedQuery("HibernateUser_findWithIdRestriction", HibernateUser.class);
            query.setParameter("id", 622400L);
            return query.getResultList();
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

    @Override
    public List<HibernateUser> criteriaApiSearch(SearchRequest request) {
//1. Get Builder for Criteria object
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<HibernateUser> query = cb.createQuery(HibernateUser.class); //here select, where, orderBy, having
        Root<HibernateUser> root = query.from(HibernateUser.class); //here params  select * from m_users -> mapping

        /*type of future params in prepared statement*/
        ParameterExpression<String> param = cb.parameter(String.class);
        ParameterExpression<Long> userSearchParam = cb.parameter(Long.class);

        /*Provide access to fields in class that mapped to columns*/
        Expression<Long> id = root.get(HibernateUser_.id);
        Expression<String> name = root.get(HibernateUser_.userCredentials.getName());
        Expression<String> surname = root.get(HibernateUser_.userCredentials.getName()); // TODO: getSurname() don't work

        /*SQL Query customizing*/
        query
                .select(root) //select * = select method, (root) = from users
                .distinct(true)
                .where( //where
                        cb.or(
                                cb.like(name, param),  //userName like param
                                cb.like(surname, param)  //userSurname like param
                        ),
                        cb.and(
                                cb.gt(id, userSearchParam), //>0
                                cb.not(id.in(40L, 50L)) //in (40,50)
                        )
//                        ,
//                        cb.between(
//                                root.get(TestUser_.birthDate),
//                                new Timestamp(new Date().getTime()),
//                                new Timestamp(new Date().getTime())
//                        )
                )
                .orderBy(cb.desc(id));

        TypedQuery<HibernateUser> resultQuery = entityManager.createQuery(query); //prepared statement on hql
        resultQuery.setParameter(param, StringUtils.join("%", request.getQuery(), "%"));
        resultQuery.setParameter(userSearchParam, request.getId());
        return resultQuery.getResultList();
    }
}
