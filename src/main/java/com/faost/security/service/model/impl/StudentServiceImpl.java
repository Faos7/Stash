package com.faost.security.service.model.impl;

import com.faost.security.domain.model.Book;
import com.faost.security.domain.model.Group;
import com.faost.security.domain.model.Student;
import com.faost.security.domain.model.create.StudentCreateForm;
import com.faost.security.repository.model.GroupRepository;
import com.faost.security.repository.model.StudentRepository;
import com.faost.security.service.model.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    private final GroupRepository groupRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public Optional<Student> getStudentById(int id) {
        LOGGER.debug("Getting student={}", id);
        return Optional.ofNullable(studentRepository.findOne(id));
    }

    @Override
    public Optional<Student> getStudentByRFIDNumber(Long RFIDNumber) {
        LOGGER.debug("Getting student by RFIDNumber={}", RFIDNumber);
        return studentRepository.findOneByNumberRFID(RFIDNumber);
    }

    @Override
    public Optional<Student> getStudentByPhoneNumber(long phoneNumber) {
        LOGGER.debug("Getting student by phoneNumber={}", phoneNumber);
        return studentRepository.findOneByPhoneNumb(phoneNumber);
    }

    @Override
    public Collection<Student> getStudentByFirstName(String firstName) {
        LOGGER.debug("Getting all students with firstName{}", firstName);
        Collection<Student> result = null;
        for (Optional<Student> librarian : studentRepository.findManyByFirstName(firstName)){
            if (librarian.isPresent()) {
                result.add(librarian.get());
            }
        }
        return result;
    }

    @Override
    public Collection<Student> getStudentBySecondName(String secondName) {
        LOGGER.debug("Getting all students with secondName{}", secondName);
        Collection<Student> result = null;
        for (Optional<Student> librarian : studentRepository.findManyBySecondName(secondName)){
            if (librarian.isPresent()) {
                result.add(librarian.get());
            }
        }
        return result;
    }

    @Override
    public Collection<Student> getStudentByThirdName(String thirdName) {
        LOGGER.debug("Getting all students with thirdName{}", thirdName);
        Collection<Student> result = null;
        for (Optional<Student> librarian : studentRepository.findManyByThirdName(thirdName)){
            if (librarian.isPresent()) {
                result.add(librarian.get());
            }
        }
        return result;
    }

    @Override
    public Collection<Student> getAllStudents() {
        LOGGER.debug("Getting all students");
        return studentRepository.findAll(new Sort("second_name"));
    }

    @Override
    public Collection<Book> getAllStudentBooks(Long numberRFID) {
        LOGGER.debug("Getting all student books, RFID{}", numberRFID);
        Optional<Student> student = studentRepository.findOneByNumberRFID(numberRFID);
        Collection<Book> books = student.get().getBooks();
        return books;
    }

    @Override
    public Student create(StudentCreateForm form) {
        Optional<Group> group = groupRepository.findOneByName(form.getGroupName());
        Student student = new Student();
        student.setFirstName(form.getFirstName());
        student.setSecondName(form.getSecondName());
        student.setThirdName(form.getThirdName());
        student.setPhoneNumb(form.getPhoneNumber());
        student.setNumberRFID(form.getNumberRFID());
        student.setGroup(group.get());
        group.get().addStudent(student);
        groupRepository.save(group.get());
        return studentRepository.save(student);
    }
}
