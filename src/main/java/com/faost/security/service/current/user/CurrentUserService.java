package com.faost.security.service.current.user;

import com.faost.security.domain.security.CurrentUser;

public interface CurrentUserService {
    boolean canAccessUser(CurrentUser currentUser, Long userId);
}
