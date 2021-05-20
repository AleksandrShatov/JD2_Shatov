package com.noirix.repository.impl;

import com.noirix.domain.User;
import com.noirix.repository.UserColumn;
import com.noirix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// НАЗВАНИЕ УЖАСНОЕ ИЗ-ЗА ИСПОЛЬЗОВАНИЯ ИМЕНИ БИБЛИОТЕК, ТАК ДЕЛАТЬ НЕЛЬЗЯ
@Repository
@Primary
public class JdbcTemplateUserRepository implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", this::getUserRowMapper);
    }

    private User getUserRowMapper(ResultSet rs, int i) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(UserColumn.ID));
        user.setName(rs.getString(UserColumn.NAME));
        user.setSurname(rs.getString(UserColumn.SURNAME));
        user.setBirthDate(rs.getDate(UserColumn.BIRTH_DATE));
        user.setLogin(rs.getString(UserColumn.LOGIN));
        user.setWeight(rs.getFloat(UserColumn.WEIGHT));
        return user;
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
    public Double getUserExpensiveCarPrice(Integer userId) {
        return null;
    }
}
