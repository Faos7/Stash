package com.faost.security.repository.security;

import com.faost.security.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findOneByUsername(String username);
}
