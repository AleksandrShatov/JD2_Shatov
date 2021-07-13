package com.noirix.repository.hibernate;

import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.repository.CrudOperations;

public interface HibernateUserRepository extends CrudOperations<Long, HibernateUser> {
}
