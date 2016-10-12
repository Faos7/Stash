package com.faost.security;

import com.faost.security.domain.security.Role;
import com.faost.security.domain.security.User;
import com.faost.security.repository.security.RoleRepository;
import com.faost.security.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by re5 on 19.10.16.
 */
@Component
public class DemoSetup {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    private void setupData(){
        Role roleUser = new Role();
        roleUser.setName("USER");
        roleUser = roleRepository.save(roleUser);
        Role roleAdmin = new Role();
        roleAdmin.setName("ADMIN");
        roleAdmin= roleRepository.save(roleAdmin);

        final User john = new User();
        john.setUsername("John");
        john.setPassword("Johns_password");
        john.setRole(roleUser);
        userRepository.save(john);

        final User tom = new User();
        tom.setUsername("Tom");
        tom.setPassword("Toms_password");
        tom.setRole(roleAdmin);
        userRepository.save(tom);

    }
}
