package com.faost.security.domain.security;

import io.katharsis.resource.annotations.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Simple JavaBean object that represents role of {@link User}.
 *
 * @author Faost
 * @version 1.0
 */

@Entity
@JsonApiResource(type = "roles")
@Table(name = "roles")
public class Role {

    @Id
    @JsonApiId
    @SequenceGenerator(name="role_sequence",sequenceName="role_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="role_sequence")
    @Column(name="id", unique=true, nullable=false)
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonApiToMany
    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}