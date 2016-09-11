package com.faost.security.service.user;

import com.faost.security.domain.User;
import com.faost.security.domain.UserCreateForm;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

    void setUserBusy(User user);

    String getStatistic(User user);
}