package com.noirix.controller.rest;

import com.noirix.beans.SecurityConfig;
import com.noirix.domain.User;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.repository.UserRepository;
import com.noirix.repository.springdata.UserDataRepository;
import com.noirix.util.PrincipalUtils;
import com.noirix.util.UserGenerator;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor
//@ApiResponses(value = {
//        @ApiResponse(code = 200, message = "Request was successfully performed!"),
//        @ApiResponse(code = 500, message = "Internal server error! https://stackoverflow.com/questions/37405244/how-to-change-the-response-status-code-for-successful-operation-in-swagger")
//})
public class UserRestController {

    private final UserRepository userRepository;

    private final UserGenerator userGenerator;

    private final SecurityConfig config;

    private final PrincipalUtils principalUtils;

    private final UserDataRepository userDataRepository;

//    @GetMapping
//    public List<User> findAll() {
//        System.out.println("In rest controller");
//        return userRepository.findAll();
//    }

    @GetMapping
    public Page<HibernateUser> findAll() {
        System.out.println("In rest controller");
        return userDataRepository.findAll(PageRequest.of(1, 10, Sort.by(Sort.Direction.DESC, "id")));
    }

    @GetMapping("/test/{userId}")
    public List<HibernateUser> searchTest(@PathVariable Long userId) {
        System.out.println("In rest controller");
        return userDataRepository.findByIdHQLVersion(userId);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Secret-Key", dataType = "string", paramType = "header",
                    value = "Secret header for secret functionality!! Hoho"),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/hello")
    public List<User> securedFindAll(HttpServletRequest request, @ApiIgnore Principal principal) {
        String userName = principalUtils.getUsername(principal);
        String secretKey = request.getHeader("Secret-Key");

        if (StringUtils.isNotBlank(secretKey) && secretKey.equals(config.getSecretKey())) {
            return Collections.singletonList(userRepository.findByLogin(userName));
        } else {
            //throw new UnauthorizedException();
            return Collections.emptyList();
        }
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", dataType = "string", paramType = "query", value = "Limit users in result list"),
            @ApiImplicitParam(name = "query", dataType = "string", paramType = "query", value = "Search query")
    })
    @GetMapping("/search")
    public List<User> userSearch(@RequestParam Integer limit, @RequestParam String query) {
        return userRepository.findUsersByQuery(limit, query);
    }

    @ApiOperation(value = "Generate auto users in system")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usersCount", dataType = "string", paramType = "path",
                    value = "Count of generated users", required = true, defaultValue = "100")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Users was successfully created!"),
            @ApiResponse(code = 500, message = "Internal server error! https://stackoverflow.com/questions/37405244/how-to-change-the-response-status-code-for-successful-operation-in-swagger")
    })
    @PostMapping("/generate/{usersCount}")
    public List<User> generateUsers(@PathVariable("usersCount") Integer count) {
//        throw new RuntimeException("Haha!");
        List<User> generateUsers = userGenerator.generate(count);
        userRepository.batchInsert(generateUsers);

        return userRepository.findAll();
    }
}
