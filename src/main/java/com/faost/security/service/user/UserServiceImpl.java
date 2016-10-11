package com.faost.security.service.user;

import com.faost.security.domain.model.Librarian;
import com.faost.security.domain.model.Student;
import com.faost.security.domain.security.Role;
import com.faost.security.domain.security.User;
import com.faost.security.domain.security.UserCreateForm;
import com.faost.security.repository.model.LibrarianRepository;
import com.faost.security.repository.model.StudentRepository;
import com.faost.security.repository.security.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final LibrarianRepository librarianRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           StudentRepository studentRepository,
                           LibrarianRepository librarianRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.librarianRepository = librarianRepository;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        LOGGER.debug("Getting user={}", id);
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        LOGGER.debug("Getting user by email={}", email.replaceFirst("@.*", "@***"));
        return userRepository.findOneByEmail(email);
    }

    @Override
    public Collection<User> getAllUsers() {
        LOGGER.debug("Getting all users");
        return userRepository.findAll(new Sort("email"));
    }

    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        if (user.getRole().equals(Role.STUDENT)){
            Student student = new Student();
            student.setUser(user);
            user.setStudent(student);
            studentRepository.save(student);
        }else if (user.getRole().equals(Role.LIBRARIAN)){
            Librarian librarian = new Librarian();
            librarian.setUser(user);
            user.setLibrarian(librarian);
            librarianRepository.save(librarian);
        }
        return userRepository.save(user);
    }

}