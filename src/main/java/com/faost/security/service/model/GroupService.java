package com.faost.security.service.model;

import com.faost.security.domain.model.Group;
import com.faost.security.domain.model.Student;
import com.faost.security.domain.model.create.GroupCreateForm;

import java.util.Collection;
import java.util.Optional;

public interface GroupService {
    Optional<Group> getGroupById(int id);

    Optional<Group> getGroupByName(String name);

    Collection<Group> getAllGroups();

    Group create(GroupCreateForm form);

    Collection<Student> getAllGroupStudents(String name);
}
