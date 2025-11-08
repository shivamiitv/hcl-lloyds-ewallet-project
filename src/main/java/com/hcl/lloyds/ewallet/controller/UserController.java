package com.hcl.lloyds.ewallet.controller;

import com.hcl.lloyds.ewallet.dto.CreateUserRequest;
import com.hcl.lloyds.ewallet.entity.User;
import com.hcl.lloyds.ewallet.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User create(@RequestBody CreateUserRequest req) {
        return userService.createUser(req);
    }

    @GetMapping
    public String helloWorld(){
        return "All is well .......";
    }
}
