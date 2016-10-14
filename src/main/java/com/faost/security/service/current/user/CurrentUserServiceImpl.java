package com.faost.security.service.current.user;


import com.faost.security.domain.security.CurrentUser;
import com.faost.security.repository.model.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsService.class);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean canAccessUser(CurrentUser currentUser, Integer userId) {
        LOGGER.debug("Checking if user={} has access to user={}", currentUser, userId);
        return currentUser != null
                && (currentUser.getRole() == roleRepository.findOne(1L).getName()|| currentUser.getId().equals(userId));
    }

}
