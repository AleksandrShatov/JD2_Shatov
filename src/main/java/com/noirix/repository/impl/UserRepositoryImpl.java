package com.noirix.repository.impl;

import com.noirix.domain.User;
import com.noirix.exception.NoSuchEntityException;
import com.noirix.repository.UserRepository;
import com.noirix.util.DatabasePropertiesReader;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.noirix.util.DatabasePropertiesReader.*;

//@Component
//@Repository("UserRep")
@Repository
public class UserRepositoryImpl implements UserRepository {

    private DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String BIRTH_DATE = "birth_date";
    public static final String LOGIN = "login";
    public static final String WEIGHT = "weight";

    private String jdbcURL = reader.getProperty(DATABASE_URL);
    private String login = reader.getProperty(DATABASE_LOGIN);
    private String password = reader.getProperty(DATABASE_PASSWORD);

    private void loadDriver() {
        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }
    }

    @Override
    public List<User> findAll() {
        final String findAllQuery = "select * from users order by id desc";

        List<User> result = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet rs;

        loadDriver();

        try {
            connection = DriverManager.getConnection(jdbcURL, login, password);
            statement = connection.createStatement();
            rs = statement.executeQuery(findAllQuery);

            //Row mapping
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(ID));
                user.setName(rs.getString(NAME));
                user.setSurname(rs.getString(SURNAME));
                user.setLogin(rs.getString(LOGIN));
                user.setBirthDate(rs.getDate(BIRTH_DATE));
                user.setWeight(rs.getFloat(WEIGHT));

                result.add(user);
            }

            return result;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public User findOne(Long id) {
        final String findById = "select * from users where id = ?";

        Connection connection;
        PreparedStatement statement;
        ResultSet rs;

        loadDriver();

        try {
            connection = DriverManager.getConnection(jdbcURL, login, password);
            statement = connection.prepareStatement(findById);
            statement.setLong(1, id);
            rs = statement.executeQuery();

            //Row mapping
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(ID));
                user.setName(rs.getString(NAME));
                user.setSurname(rs.getString(SURNAME));
                user.setLogin(rs.getString(LOGIN));
                user.setBirthDate(rs.getDate(BIRTH_DATE));
                user.setWeight(rs.getFloat(WEIGHT));

                return user;
            }

            throw new NoSuchEntityException("No such user with id: " + id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public User save(User user) {
        final String insertQuery = "insert into users (name, surname, birth_date, login, weight) " + "values (?,?,?,?,?)";

        Connection connection;
        PreparedStatement statement;

        loadDriver();

        try {
            connection = DriverManager.getConnection(jdbcURL, login, password);
            statement = connection.prepareStatement(insertQuery);

            PreparedStatement lastInsertId = connection.prepareStatement("select currval('users_id_seq') as last_insert_id");

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setDate(3, new Date(user.getBirthDate().getTime()));
            statement.setString(4, user.getLogin());
            statement.setFloat(5, user.getWeight());

            statement.executeUpdate();

            Long insertedId;
            ResultSet lastIdResultSet = lastInsertId.executeQuery();
            if (lastIdResultSet.next()) {
                insertedId = lastIdResultSet.getLong("last_insert_id");
            } else {
                throw new RuntimeException("We cannot read sequence last value during User creation!");
            }

            return findOne(insertedId);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public User update(User userForUpdate) {
        final String updateQuery = "update users set name = ?, surname = ?, birth_date = ?, login = ?, weight = ? where id = ?";

        Connection connection;
        PreparedStatement statement;

        loadDriver();

        try {
            connection = DriverManager.getConnection(jdbcURL, login, password);
            statement = connection.prepareStatement(updateQuery);

            statement.setString(1, userForUpdate.getName());
            statement.setString(2, userForUpdate.getSurname());
            statement.setDate(3, userForUpdate.getBirthDate());
            statement.setString(4, userForUpdate.getLogin());
            statement.setFloat(5, userForUpdate.getWeight());
            statement.setLong(6, userForUpdate.getId());
            int rows = statement.executeUpdate();

            if (rows < 1) {
                throw new NoSuchEntityException("No such user with id: " + userForUpdate.getId());
            }

            return findOne(userForUpdate.getId());

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public void delete(Long id) {
        final String deleteQuery = "delete from users where id = ?";

        Connection connection;
        PreparedStatement statement;

        loadDriver();

        try {
            connection = DriverManager.getConnection(jdbcURL, login, password);
            statement = connection.prepareStatement(deleteQuery);

            statement.setLong(1, id);

            if (!findOne(id).equals(null)) {
                statement.executeUpdate();
            } else {
                throw new RuntimeException("User with id = '" + id + "' does not exist!");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public List<User> findUsersByQuery(String query) {

        List<User> result = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet rs;

        loadDriver();

        try {
            connection = DriverManager.getConnection(jdbcURL, login, password);
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            //Row mapping
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(ID));
                user.setName(rs.getString(NAME));
                user.setSurname(rs.getString(SURNAME));
                user.setLogin(rs.getString(LOGIN));
                user.setBirthDate(rs.getDate(BIRTH_DATE));
                user.setWeight(rs.getFloat(WEIGHT));

                result.add(user);
            }

            return result;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Double getUserExpensiveCarPrice(Integer userId) {
        final String findPriceFunction = "select get_user_expensive_car(?)";

        Connection connection;
        PreparedStatement statement;
        ResultSet rs;

        loadDriver();

        try {
            connection = DriverManager.getConnection(jdbcURL, login, password);
            statement = connection.prepareStatement(findPriceFunction);
            statement.setInt(1, userId);
            rs = statement.executeQuery();

            //Row mapping
            rs.next();
            return rs.getDouble("get_user_expensive_car");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

}
