package com.faost.security.service.user;

import com.faost.security.domain.security.User;
import com.faost.security.domain.security.UserCreateForm;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(Long id);

    Optional<User> getUserByUsername(String username);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

    Collection<User> getAllUsersWithSpecifiedRole(String role);

    Collection<User> getAllGroupStudents(int id);

    Collection<User> getAllLibrarians(int id);

}