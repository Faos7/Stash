package com.faost.security.domain.security;

import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User{

    private User user;

    public CurrentUser(User user) {
        super(user.getUsername()
                , user.getPassword()
                , AuthorityUtils.createAuthorityList(user.getRole().getName()));
        this.user = user;
    }

    public  User getUser(){
        return user;
    }

    public Long getId(){
        return user.getId();
    }

    public String getRole() {
        return user.getRole().getName();
    }

}
