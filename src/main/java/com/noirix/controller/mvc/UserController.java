package com.noirix.controller.mvc;

import com.noirix.beans.SecurityConfig;
import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.domain.User;
import com.noirix.repository.UserRepository;
import com.noirix.util.UserGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserGenerator userGenerator;
    private final SecurityConfig config;

    //GET + /hello
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView helloHandler(HttpServletRequest request) {

        String secretKey = request.getHeader("Secret-Key");

        if (StringUtils.isNotBlank(secretKey) && secretKey.equals(config.getSecretKey())) {
            List<User> users = userRepository.findAll();
            return new ModelAndView("bye", Collections.singletonMap("users", users));
        } else {
            return new ModelAndView("error", Collections.emptyMap());
        }

    }

    //GET + /search?limit=100&query=a
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView userSearch(@RequestParam Integer limit, @RequestParam String query) {

        List<User> users = userRepository.findUsersByQuery(limit, query);
        return new ModelAndView("bye", Collections.singletonMap("users", users));
    }

    //POST localhost:8080/JD2_Shatov/users?login=slavka&weight=90
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    //Jackson -> Spring REST -> object to JSON -> JSON to Object
    //@ModelAttribute -> query params to object
    public ModelAndView createUsers(@ModelAttribute UserCreateRequest createRequest) {

        User generatedUser = userGenerator.generate();
        generatedUser.setName(createRequest.getName());
        generatedUser.setSurname(createRequest.getSurname());
        generatedUser.setWeight(createRequest.getWeight());
        generatedUser.setLogin(createRequest.getLogin());

        userRepository.save(generatedUser);

        List<User> users = userRepository.findAll();
        return new ModelAndView("bye", Collections.singletonMap("users", users));
    }

    //POST localhost:8080/studentsdemo/users/generate/100
    @RequestMapping(value = "/users/generate/{usersCount}", method = RequestMethod.POST)
    public void generateUsers(@PathVariable("usersCount") Integer count) {
        List<User> generateUsers = userGenerator.generate(count);
        userRepository.batchInsert(generateUsers);
    }
}
