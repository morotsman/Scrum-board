package org.github.morotsman.todo.model.unit_of_work;



import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="WORK_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class Work {

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
    @JoinTable(
            name = "PARENT_WORK" ,
            joinColumns = {@JoinColumn(name="PARENT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "WORK_ID")}
    )
    private Work parent;






    public Work() {
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

    public Work getParent() {
        return parent;
    }

    public void setParent(Work parent) {
        this.parent = parent;
    }

    public abstract Long numberOfTasks();

    /*

    public abstract Long numberOfTasks();

    public abstract Long numberOfCompletedTasks();

    public abstract Long numberOfRemainingTask();

    public abstract List<String> remainingTaskNames();

    public abstract Long totalEstimatedTime();

    public abstract Long totalRemainingTime();

    public abstract Long totalCompletedTime();
    */

}
