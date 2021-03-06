package com.noirix.repository.impl;

import com.noirix.domain.Role;
import com.noirix.domain.User;
import com.noirix.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static com.noirix.repository.RoleColumn.ROLE_ID;
import static com.noirix.repository.RoleColumn.ROLE_NAME;

@Repository
@Primary
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Role getRoleRowMapper(ResultSet resultSet, int i) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getLong(ROLE_ID));
        role.setRoleName(resultSet.getString(ROLE_NAME));
        return role;
    }

    @Override
    public List<Role> findAll() {
        return jdbcTemplate.query("select * from roles order by id desc", this::getRoleRowMapper);
    }

    @Override
    public Role findOne(Long id) {
        final String findOneWithNameParam = "select * from roles where id = :roleId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("roleId", id);

        return namedParameterJdbcTemplate.queryForObject(findOneWithNameParam, params, this::getRoleRowMapper);
    }

    @Override
    public Role save(Role entity) {
        final String createQuery = "insert into roles (role_name) " +
                "values (:roleName);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("roleName", entity.getRoleName());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder, new String[]{"id"});

        long createdRoleId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findOne(createdRoleId);
    }

    @Override
    public void addOne(Role entity) {

    }

    @Override
    public void save(List<Role> entities) {

    }

    @Override
    public Role update(Role entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Role> getUserRoles(User user) {
        final String findOneWithNameParam = "select r.id as id, r.role_name as role_name from user_roles " +
                "inner join roles r on user_roles.role_id = r.id where user_roles.user_id = :userId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", user.getId());

        return namedParameterJdbcTemplate.query(findOneWithNameParam, params, this::getRoleRowMapper);
    }
}
