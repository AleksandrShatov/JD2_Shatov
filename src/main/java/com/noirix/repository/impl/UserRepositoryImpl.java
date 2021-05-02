package com.noirix.repository.impl;

import com.noirix.domain.User;
import com.noirix.repository.UserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    public static final String DATABASE_URL = "jdbc::postgresql://localhost:";
    public static final int DATABASE_PORT = 5432;
    public static final String DATABASE_NAME = "/student_demo";
    public static final String DATABASE_LOGIN = "postgres";
    public static final String DATABASE_PASSWORD = "Postgres";

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findOne(Long id) {
        return null;
    }

    @Override
    public User save(User entity) {
        return null;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<User> findUsersByQuery(String query) {
        return null;
    }

    @Override
    public List<User> getPage(int limit, int offset) {
        return null;
    }
}
