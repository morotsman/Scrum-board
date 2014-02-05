package org.github.morotsman.todo.infrastructure.persistance.hibernate;

import org.github.morotsman.todo.model.unit_of_work.Story;
import org.github.morotsman.todo.model.unit_of_work.StoryRepository;
import org.github.morotsman.todo.model.unit_of_work.Task;
import org.github.morotsman.todo.model.unit_of_work.TaskRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepositoryImpl extends GenericHibernateDao<Task,Long> implements TaskRepository {

}
