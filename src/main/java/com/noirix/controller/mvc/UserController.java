package com.noirix.controller.mvc;

import com.noirix.domain.User;
import com.noirix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    //GET + /hello
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView helloHandler() {

        List<User> users = userRepository.findAll();

        return new ModelAndView("bye", Collections.singletonMap("users", users));

    }
}
