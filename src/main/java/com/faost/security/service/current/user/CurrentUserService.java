package com.faost.security.service.current.user;

import com.faost.security.domain.CurrentUser;

public interface CurrentUserService {
    boolean canAccessUser(CurrentUser currentUser, Integer userId);
}
