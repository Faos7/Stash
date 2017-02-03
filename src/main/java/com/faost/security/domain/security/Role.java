package com.faost.security.domain.security;

/**
 * Simple JavaBean object that represents role of {@link User}.
 *
 * @author Faost
 * @version 1.0
 */

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;



@Entity
@Table(name = "roles")
public class Role {

    @Id
    @SequenceGenerator(name="role_sequence",sequenceName="role_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="role_sequence")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public Role() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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


}