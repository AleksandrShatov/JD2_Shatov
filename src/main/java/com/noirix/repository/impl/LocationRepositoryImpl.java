package com.noirix.repository.impl;

import com.noirix.domain.Location;
import com.noirix.exception.NoSuchEntityException;
import com.noirix.repository.LocationRepository;
import com.noirix.util.DatabasePropertiesReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.noirix.util.DatabasePropertiesReader.*;

public class LocationRepositoryImpl implements LocationRepository {

    private DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    private static final String ID = "id";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";

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
    public List<Location> findAll() {
        final String findAllQuery = "select * from locations order by id desc";

        List<Location> result = new ArrayList<>();

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
                Location location = new Location();
                location.setId(rs.getLong(ID));
                location.setCountry(rs.getString(COUNTRY));
                location.setCity(rs.getString(CITY));
                location.setLatitude(rs.getDouble(LATITUDE));
                location.setLongitude(rs.getDouble(LONGITUDE));

                result.add(location);
            }

            return result;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Location findOne(Long id) {
        final String findById = "select * from locations where id = ?";

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
                Location location = new Location();
                location.setId(rs.getLong(ID));
                location.setCountry(rs.getString(COUNTRY));
                location.setCity(rs.getString(CITY));
                location.setLatitude(rs.getDouble(LATITUDE));
                location.setLongitude(rs.getDouble(LONGITUDE));

                return location;
            }

            throw new NoSuchEntityException("No such location with id: " + id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Location save(Location location) {
        final String insertQuery = "insert into locations (country, city, latitude, longitude)" + "values (?,?,?,?)";
        Connection connection;
        PreparedStatement statement;

        loadDriver();

        try {
            connection = DriverManager.getConnection(jdbcURL, login, password);
            statement = connection.prepareStatement(insertQuery);

            PreparedStatement lastInsertId = connection.prepareStatement("select currval('location_id_seq') as last_insert_id");

            statement.setString(1, location.getCountry());
            statement.setString(2, location.getCity());
            statement.setDouble(3, location.getLatitude());
            statement.setDouble(4, location.getLongitude());

            statement.executeUpdate();

            Long insertedId;
            ResultSet lastIdResultSet = lastInsertId.executeQuery();
            if (lastIdResultSet.next()) {
                insertedId = lastIdResultSet.getLong("last_insert_id");
            } else {
                throw new RuntimeException("We cannot read sequence last value during Location creation!");
            }

            return findOne(insertedId);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Location update(Location locationForUpdate) {
        final String updateQuery = "update locations set country = ?, city = ?, latitude = ?, longitude = ? where id = ?";

        Connection connection;
        PreparedStatement statement;

        loadDriver();

        try {
            connection = DriverManager.getConnection(jdbcURL, login, password);
            statement = connection.prepareStatement(updateQuery);

            statement.setString(1, locationForUpdate.getCountry());
            statement.setString(2, locationForUpdate.getCity());
            statement.setDouble(3, locationForUpdate.getLatitude());
            statement.setDouble(4, locationForUpdate.getLongitude());
            statement.setLong(5, locationForUpdate.getId());
            int rows = statement.executeUpdate();

            if (rows < 1) {
                throw new NoSuchEntityException("No such location with id: " + locationForUpdate.getId());
            }

            return findOne(locationForUpdate.getId());

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }


    }

    @Override
    public void delete(Long id) {
        final String deleteQuery = "delete from locations where id = ?";

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
                throw new RuntimeException("Location with id = '" + id + "' does not exist!");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }


    }

    @Override
    public List<Location> findLocationsByQuery(String query) {

        List<Location> result = new ArrayList<>();

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
                Location location = new Location();
                location.setId(rs.getLong(ID));
                location.setCountry(rs.getString(COUNTRY));
                location.setCity(rs.getString(CITY));
                location.setLatitude(rs.getDouble(LATITUDE));
                location.setLongitude(rs.getDouble(LONGITUDE));

                result.add(location);
            }

            return result;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Set<String> getAllCountries() {
        final String getAllCountriesQuery = "select distinct country from locations";

        Set<String> result = new HashSet<>();

        Connection connection;
        Statement statement;
        ResultSet rs;

        loadDriver();

        try {
            connection = DriverManager.getConnection(jdbcURL, login, password);
            statement = connection.createStatement();
            rs = statement.executeQuery(getAllCountriesQuery);

            //Row mapping
            while (rs.next()) {
                result.add(rs.getString(COUNTRY));
            }

            return result;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }
}
