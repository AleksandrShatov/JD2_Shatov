package com.noirix.repository;

import com.noirix.domain.User;

import java.util.List;

public interface UserRepository extends CrudOperations<Long, User> {

    List<User> findUsersByQuery(String query);

    List<User> getPage(int limit, int offset);

}
