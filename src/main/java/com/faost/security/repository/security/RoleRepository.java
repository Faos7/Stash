package com.faost.security.repository.security;

import com.faost.security.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by re5 on 19.10.16.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

}
