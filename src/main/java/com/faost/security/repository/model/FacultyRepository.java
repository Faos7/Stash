package com.faost.security.repository.model;

import com.faost.security.domain.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by faos7 on 27.09.16.
 */
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    Optional<Faculty> findOneByName(String name);
}
