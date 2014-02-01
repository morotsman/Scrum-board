package org.github.morotsman.todo.model.team;


import org.github.morotsman.todo.model.membership.Membership;
import org.github.morotsman.todo.model.sprint.Sprint;
import org.github.morotsman.todo.model.user.User;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"TEAM_NAME"})
})
public class Team {


    @OneToMany(mappedBy = "team")
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    Set<Membership> memberships = new HashSet<Membership>();


    @OneToMany(mappedBy = "team")
    private Set<Sprint> sprints = new HashSet<Sprint>();

    @Id
    @GeneratedValue
    private Long id;


    @Column(length = 255, nullable = false,name="TEAM_NAME")
    private String name;

    @Column(length = 255, nullable = false)
    private String description;


    public Team(){

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(Set<Membership> memberships) {
        this.memberships = memberships;
    }

    public Set<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(Set<Sprint> sprints) {
        this.sprints = sprints;
    }
}
