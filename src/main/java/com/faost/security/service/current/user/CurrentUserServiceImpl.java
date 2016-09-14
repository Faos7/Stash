package com.faost.security.service.current.user;


import com.faost.security.domain.security.CurrentUser;
import com.faost.security.domain.security.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsService.class);

    @Override
    public boolean canAccessUser(CurrentUser currentUser, Integer userId) {
        LOGGER.debug("Checking if user={} has access to user={}", currentUser, userId);
        return currentUser != null
                && (currentUser.getRole() == Role.ADMIN|| currentUser.getId().equals(userId));
    }

}
