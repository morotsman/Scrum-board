package org.github.morotsman.todo.model.sprint;


import org.github.morotsman.todo.model.team.Team;
import org.github.morotsman.todo.model.unit_of_work.Story;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints=
@UniqueConstraint(columnNames={"TEAM_ID", "NAME"}))
public class Sprint {


    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    @JoinColumn(name="TEAM_ID", nullable = false)
    private Team team;

    @Column(length = 255, nullable = false, name="NAME")
    private String name;

    @OneToMany(mappedBy = "sprint")
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    private Set<Story> backlog = new HashSet<Story>();

    public Sprint() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Story> getBacklog() {
        return backlog;
    }

    public void setBacklog(Set<Story> backlog) {
        this.backlog = backlog;
    }
}
