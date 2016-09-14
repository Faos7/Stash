package com.faost.security.repository.model;

import com.faost.security.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Collection<Optional<Student>> findManyByFirstName(String firstName);

    Collection<Optional<Student>> findManyBySecondName(String secondName);

    Collection<Optional<Student>> findManyByThirdName(String thirdName);

    Optional<Student> findOneByRFIDNumber(long RFIDNumber);

    Optional<Student> findOneByPhoneNumb(long phoneNumb);
}
