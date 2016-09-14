package com.faost.security.repository.model;

import com.faost.security.domain.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Library, Integer> {

    Optional<Library> findOneByName(String name);
}
