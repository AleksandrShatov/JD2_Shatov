package com.noirix;

import com.noirix.domain.User;
import com.noirix.repository.LocationRepository;
import com.noirix.repository.UserRepository;
import com.noirix.repository.impl.LocationRepositoryImpl;
import com.noirix.repository.impl.UserRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl();
        LocationRepository locationRepository = new LocationRepositoryImpl();

        testUserRepository(userRepository);

        testLocationRepository(locationRepository);

    }

    static void testUserRepository(UserRepository userRepository) {

//         userRepository.findAll().stream().forEach(User::toString);

        // Find all users
//        System.out.println("Find all users");
//        for (User user : userRepository.findAll()) {
//            System.out.println(user);
//        }

        // Find one user
//        System.out.println("Find one user");
//        try {
//            System.out.println(userRepository.findOne(20L));
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }

        // Save user
//        User user = new User();
//        user.setName("Test12");
//        user.setSurname("Save12");
//        user.setLogin("test_save_12");
//        user.setWeight(95f);
//        user.setBirthDate(new Date(11000000L));
//        System.out.println("Save user");
//        System.out.println(userRepository.save(user));

        // Update user
//        System.out.println("Update user");
//        User userUp = userRepository.findOne(11L);
//        userUp.setName("Update10");
//        userUp.setSurname("Up10");
//        userUp.setLogin("update10_up10_11");
//        userUp.setWeight(110f);
//        userUp.setBirthDate(new Date(101010101L));
//        userRepository.update(userUp);

        // Delete user
//        System.out.println("Delete user");
//        userRepository.delete(22L);

        // Find users by Query
        System.out.println("Find users by Query");
        String query = "select * from users where id > 10 and name like '%es%'";
        for (User user : userRepository.findUsersByQuery(query)) {
            System.out.println(user);
        }

        // Check function call for user
//        System.out.println("Check function call");
//        System.out.println(userRepository.getUserExpensiveCarPrice(12));
    }

    static void testLocationRepository(LocationRepository locationRepository) {

//        locationRepository.findAll().stream().forEach(Location::toString);

        // Find all locations
//        System.out.println("Find all locations");
//        for (Location location: locationRepository.findAll()) {
//            System.out.println(location);
//        }

        // Find one location
//        System.out.println("Find one location");
//        try {
//            System.out.println(locationRepository.findOne(2L));
//            System.out.println(locationRepository.findOne(20L));
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }

        // Save location
//        Location location = new Location();
//        location.setCountry("Country33");
//        location.setCity("City33");
//        location.setLatitude(303030.0);
//        location.setLongitude(330330.0);
//        System.out.println("Save location");
//        System.out.println(locationRepository.save(location));

        // Update location
//        System.out.println("Update location");
//        Location locationUp = locationRepository.findOne(4L);
//        locationUp.setCountry("Country11");
//        locationUp.setCity("City11");
//        locationUp.setLatitude(111111.0);
//        locationUp.setLongitude(100100.0);
//        locationRepository.update(locationUp);

        // Delete location
//        System.out.println("Delete location");
//        locationRepository.delete(5L);

        // Find locations by Query
//        System.out.println("Find locations by Query");
//        String query = "select * from locations where id < 4";
//        for (Location location : locationRepository.findLocationsByQuery(query)) {
//            System.out.println(location);
//        }

        // Check getAllCountries()
        System.out.println("Check getAllCountries()");
        for (String str : locationRepository.getAllCountries()) {
            System.out.println(str);
        }
    }
}
