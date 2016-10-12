package com.faost.security.repository.security;

import com.faost.security.domain.security.User;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by re5 on 19.10.16.
 */
public class UserResourseRepository implements ResourceRepository<User, Long> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findOne(Long id, QueryParams queryParams) {
        return userRepository.findOne(id);
    }

    @Override
    public Iterable<User> findAll(QueryParams queryParams) {
        return userRepository.findAll();
    }

    @Override
    public Iterable<User> findAll(Iterable<Long> ids, QueryParams queryParams) {
        return userRepository.findAll(ids);
    }

    @Override
    public <S extends User> S save(S entity) {
        return userRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }
}
