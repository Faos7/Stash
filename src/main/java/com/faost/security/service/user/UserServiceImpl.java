package com.faost.security.service.user;

import com.faost.security.domain.model.Group;
import com.faost.security.domain.model.Library;
import com.faost.security.domain.security.Role;
import com.faost.security.domain.security.User;
import com.faost.security.domain.security.UserCreateForm;
import com.faost.security.repository.model.GroupRepository;
import com.faost.security.repository.model.LibraryRepository;
import com.faost.security.repository.model.RoleRepository;
import com.faost.security.repository.security.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final GroupRepository groupsRepository;
    private final LibraryRepository libraryRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           GroupRepository groupsRepository,
                           LibraryRepository libraryRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.libraryRepository = libraryRepository;
        this.groupsRepository = groupsRepository;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        LOGGER.debug("Getting user={}", id);
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByUsername (String email) {
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
        Role role = roleRepository.findOneByName(form.getRole());
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public Collection<User> getAllUsersWithSpecifiedRole(String role) {
        LOGGER.debug("Getting all users with role={}", role);
        Role role1 = roleRepository.findOneByName(role);
        Collection<User> users = role1.getUsers();

        return users;
    }

    @Override
    public Collection<User> getAllGroupStudents(int id) {
        LOGGER.debug("Getting all users with group={}", id);
        Group group = groupsRepository.findOne(id);
        Collection<User> users = group.getUsers();

        return users;
    }

    @Override
    public Collection<User> getAllLibrarians(int id) {
        LOGGER.debug("Getting all users with library={}", id);
        Library library = libraryRepository.findOne(id);
        Collection<User> users = library.getUsers();

        return users;
    }
}