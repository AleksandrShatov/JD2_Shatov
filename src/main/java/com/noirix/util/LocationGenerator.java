package com.noirix.util;

import com.noirix.domain.Location;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationGenerator {

    public List<Location> generate(int count) {
        List<Location> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(generate());
        }
        return result;
    }

    public Location generate() {
        Location location = new Location();
        location.setCountry(RandomStringUtils.randomAlphanumeric(10));
        location.setCity(RandomStringUtils.randomAlphanumeric(10));
        location.setLatitude(RandomUtils.nextDouble(-90.0, 90.0));
        location.setLongitude(RandomUtils.nextDouble(-180.0, 180.0));
        return location;
    }
}
