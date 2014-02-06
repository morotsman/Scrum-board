package org.github.morotsman.todo.model.unit_of_work;



import javax.persistence.*;

@Entity
@Table(name = "TASK",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"STORY_ID", "NAME"}))
public class Task {


    @Id
    @GeneratedValue
    @Column(name = "WORK_ID")
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Long estimate;

    @ManyToOne
    @JoinColumn(name="STORY_ID", nullable = false)
    private Story story;


    public Task() {
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

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }
}
