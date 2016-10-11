package com.faost.security.service.model;

import com.faost.security.domain.model.Book;
import com.faost.security.domain.model.Student;
import com.faost.security.domain.model.create.StudentCreateForm;

import java.util.Collection;
import java.util.Optional;

public interface StudentService {

    Optional<Student> getStudentById(int id);

    Optional<Student> getStudentByRFIDNumber(Long RFIDNumber);

    Optional<Student> getStudentByPhoneNumber(long phoneNumber);

    Collection<Student> getStudentByFirstName(String firstName);

    Collection<Student> getStudentBySecondName(String secondName);

    Collection<Student> getStudentByThirdName(String thirdName);

    Collection<Student> getAllStudents();

    Student create(StudentCreateForm form);

    Collection<Book> getAllStudentBooks(Long numberRFID);
}
