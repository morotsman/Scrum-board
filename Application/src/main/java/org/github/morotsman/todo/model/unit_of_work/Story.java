package org.github.morotsman.todo.model.unit_of_work;



import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "STORY")
@DiscriminatorValue("S")
public class Story extends Work{


    @OneToMany(mappedBy = "parent")
    private Set<Work> works = new HashSet<Work>();


    @Column
    private boolean bug;


    public Story(){}



    public boolean isBug() {
        return bug;
    }

    public void setBug(boolean bug) {
        this.bug = bug;
    }

    public Set<Work> getWorks() {
        return works;
    }

    public void setWorks(Set<Work> works) {
        this.works = works;
    }

    @Override
    public Long numberOfTasks() {
        Long result = 0L;
        for(Work each:works){
            result += each.numberOfTasks();
        }
        return result;
    }
}
