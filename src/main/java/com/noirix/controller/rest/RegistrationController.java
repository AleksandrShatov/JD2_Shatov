package com.noirix.controller.rest;

import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.domain.Role;
import com.noirix.domain.User;
import com.noirix.repository.RoleRepository;
import com.noirix.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @ApiOperation(value = "Creating one user")
    @PostMapping
    public User createUser(@RequestBody UserCreateRequest createRequest) {

        User newUser = new User();

        newUser.setWeight(createRequest.getWeight());
        newUser.setLogin(createRequest.getLogin());
        newUser.setName(createRequest.getName());
        newUser.setSurname(createRequest.getSurname());
        newUser.setPassword(createRequest.getPassword());

        User savedUser = userRepository.save(newUser);

        List<Role> roles = roleRepository.findAll();

        userRepository.saveUserRoles(savedUser, roles);

        return savedUser;
    }
}
