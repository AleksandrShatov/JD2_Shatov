package com.noirix;

import com.noirix.domain.User;
import com.noirix.repository.UserRepository;
import com.noirix.repository.impl.UserRepositoryImpl;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl();

//        userRepository.findAll().stream().forEach(User::toString);

        // Find all users
//        for (User user : userRepository.findAll()) {
//            System.out.println(user);
//        }

        // Find one user
//        try {
//            System.out.println(userRepository.findOne(5L));
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }

        // Save user
        User user = new User();
        user.setName("Test2");
        user.setSurname("Save2");
        user.setLogin("test_save_2");
        user.setWeight(120f);
        user.setBirthDate(new Date(12000000L));

        System.out.println(userRepository.save(user));
    }
}
