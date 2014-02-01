package org.github.morotsman.todo.model.user;

import org.github.morotsman.todo.model.membership.Membership;
import org.github.morotsman.todo.model.team.Team;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"USER_NAME"})
})
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="USER_NAME")
    private String name;


    @OneToMany(mappedBy = "user")
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    Set<Membership> memberships = new HashSet<Membership>();



    public User(){}

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

    public Set<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(Set<Membership> memberships) {
        this.memberships = memberships;
    }




}
