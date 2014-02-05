package org.github.morotsman.todo.model.unit_of_work;



import javax.persistence.*;

@Entity
@Table(name = "TASK")
@DiscriminatorValue("T")
public class Task extends Work{



    public Task() {
    }

    @Override
    public Long numberOfTasks() {
        return 1L;
    }
}
