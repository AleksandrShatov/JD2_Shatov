package com.noirix.repository.impl;

import com.noirix.domain.Location;
import com.noirix.repository.LocationColumn;
import com.noirix.repository.LocationRepository;
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
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Repository
@Primary
@RequiredArgsConstructor
public class LocationRepositoryImpl implements LocationRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Location> findAll() {
        return jdbcTemplate.query("select * from locations order by id desc", this::getLocationRowMapper);
    }

    private Location getLocationRowMapper(ResultSet rs, int i) throws SQLException {
        Location location = new Location();
        location.setId(rs.getLong(LocationColumn.ID));
        location.setCountry(rs.getString(LocationColumn.COUNTRY));
        location.setCity(rs.getString(LocationColumn.CITY));
        location.setLatitude(rs.getDouble(LocationColumn.LATITUDE));
        location.setLongitude(rs.getDouble(LocationColumn.LONGITUDE));
        return location;
    }

    @Override
    public Location findOne(Long id) {
        //final String findOneWithWildcard = "select * from locations where id = ?";

        final String findOneWithNameParam = "select * from locations where id = :idName";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idName", id);

        return namedParameterJdbcTemplate.queryForObject(findOneWithNameParam, params, this::getLocationRowMapper);
    }

    @Override
    public Location save(Location entity) {
        final String createQuery = "insert into locations (country, city, latitude, longitude) " +
                "values (:country, :city, :latitude, :longitude);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = generateLocationParamsMap(entity);

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder, new String[]{"id"});

        long createLocationId = Objects.requireNonNull(keyHolder.getKey().longValue());

        return findOne(createLocationId);
    }

    @Override
    public void addOne(Location entity) {
        final String createQuery = "insert into locations (country, city, latitude, longitude) " +
                "values (:country, :city, :latitude, :longitude);";

        MapSqlParameterSource params = generateLocationParamsMap(entity);
        namedParameterJdbcTemplate.update(createQuery, params);
    }

    @Override
    public void save(List<Location> entities) {
        for (Location entity : entities) {
            addOne(entity);
        }
    }

    private MapSqlParameterSource generateLocationParamsMap(Location entity) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("country", entity.getCountry());
        params.addValue("city", entity.getCity());
        params.addValue("latitude", entity.getLatitude());
        params.addValue("longitude", entity.getLongitude());

        return params;
    }

    @Override
    public Location update(Location locationForUpdate) {
        return null;
    }

    @Override
    public void delete(Long id) { //TODO
        final String deleteQuery = "delete from locations where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        namedParameterJdbcTemplate.update(deleteQuery, params);
    }

    @Override
    public List<Location> findLocationsByQuery(Integer limit, String query) {
        return null;
    }

    @Override
    public Set<String> getAllCountries() {
        final String getAllCountriesQuery = "select distinct country from locations";

        Set<String> result = new HashSet<>();

        return null;
    }
}
