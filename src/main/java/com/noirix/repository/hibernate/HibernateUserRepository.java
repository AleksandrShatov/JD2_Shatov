package com.noirix.repository.hibernate;

import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.repository.CrudOperations;

import java.util.List;

public interface HibernateUserRepository extends CrudOperations<Long, HibernateUser> {

    List<HibernateUser> criteriaApiSearch();
}
