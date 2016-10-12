package com.faost.security.controller;

import com.faost.security.repository.security.UserRepository;
import com.faost.security.repository.security.UserResourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by re5 on 19.10.16.
 */
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List getUsers(){
        return userRepository.findAll();
    }

}
