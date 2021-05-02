package com.noirix;

import com.noirix.domain.User;
import com.noirix.repository.UserRepository;
import com.noirix.repository.impl.UserRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl();

//        userRepository.findAll().stream().forEach(User::toString);

        for (User user : userRepository.findAll()) {
            System.out.println(user);
        }

    }
}
