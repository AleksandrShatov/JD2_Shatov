package com.noirix.repository.hibernate;

import com.noirix.domain.hibernate.HibernateLocation;
import com.noirix.repository.CrudOperations;

public interface HibernateLocationRepository extends CrudOperations<Long, HibernateLocation> {

    Object strangeSearch();
}
