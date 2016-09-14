package com.faost.security.repository.model;

import com.faost.security.domain.model.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University, Integer> {

    Optional<University> findOneByName(String name);
}