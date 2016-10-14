package com.faost.security.service.model;

import com.faost.security.domain.model.Group;
import com.faost.security.domain.model.create.GroupCreateForm;
import com.faost.security.domain.security.User;

import java.util.Collection;
import java.util.Optional;

public interface GroupService {
    Optional<Group> getGroupById(int id);

    Optional<Group> getGroupByName(String name);

    Collection<Group> getAllGroups();

    Group create(GroupCreateForm form);

    Collection<User> getAllGroupStudents(String name);
}
