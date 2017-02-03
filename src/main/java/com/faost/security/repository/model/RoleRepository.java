package com.faost.security.repository.model;

import com.faost.security.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by faos7 on 26.12.16.
 */
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findOneByName(String name);
}