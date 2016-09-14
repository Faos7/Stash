package com.faost.security.repository.model;

import com.faost.security.domain.model.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {

    Collection<Optional<Librarian>> findManyByFirstName(String firstName);

    Collection<Optional<Librarian>> findManyBySecondName(String secondName);

    Collection<Optional<Librarian>> findManyByThirdName(String thirdName);
}
