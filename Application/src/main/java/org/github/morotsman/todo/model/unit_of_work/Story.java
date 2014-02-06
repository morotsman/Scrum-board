package org.github.morotsman.todo.model.unit_of_work;



import org.github.morotsman.todo.model.sprint.Sprint;
import org.github.morotsman.todo.model.team.Team;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "STORY",
uniqueConstraints=
@UniqueConstraint(columnNames={"TEAM_ID", "NAME"}))
public class Story {


    @Id
    @GeneratedValue
    @Column(name = "STORY_ID")
    private Long id;

    @OneToMany(mappedBy = "story")
    private Set<Task> tasks = new HashSet<Task>();


    @Column
    private boolean bug;

    @ManyToOne
    @JoinColumn(name="TEAM_ID", nullable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(name="SPRINT_ID")
    private Sprint sprint;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Long estimate;


    public Story(){}



    public boolean isBug() {
        return bug;
    }

    public void setBug(boolean bug) {
        this.bug = bug;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void addTeam(Team team){
        setTeam(team);
        team.getBacklog().add(this);
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public void addSprint(Sprint sprint){
        setSprint(sprint);
        sprint.getBacklog().add(this);
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

    public Long getEstimate() {
        return estimate;
    }

    public void setEstimate(Long estimate) {
        this.estimate = estimate;
    }
}
