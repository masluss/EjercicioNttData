package com.api.users.controller;

import com.api.users.domains.request.UserRequest;
import com.api.users.domains.response.Response;
import com.api.users.entity.User;
import com.api.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/getUser")
    public Response<User> getUserByTypeAndDocument(@RequestBody @Valid UserRequest userRequest){
        return userService.getUserByTypeAndDocument(userRequest);
    }
}
