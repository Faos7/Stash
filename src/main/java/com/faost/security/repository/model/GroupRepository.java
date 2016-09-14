package com.faost.security.repository.model;

import com.faost.security.domain.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    Optional<Group> findOneByName(String name);
}
