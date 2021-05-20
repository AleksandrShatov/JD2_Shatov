package com.noirix.repository;

import com.noirix.domain.User;

import java.util.List;

public interface UserRepository extends CrudOperations<Long, User> {

    List<User> findUsersByQuery(String query);

    Double getUserExpensiveCarPrice(Integer userId);

    void batchInsert(List<User> users);

}
