package com.noirix.repository;

import com.noirix.domain.Location;

import java.util.List;
import java.util.Set;

public interface LocationRepository extends CrudOperations<Long, Location> {

    List<Location> findLocationsByQuery(String query);

    Set<String> getAllCountries();

}
