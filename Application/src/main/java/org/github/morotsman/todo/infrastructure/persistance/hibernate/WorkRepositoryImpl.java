package org.github.morotsman.todo.infrastructure.persistance.hibernate;

import org.github.morotsman.todo.model.unit_of_work.Task;
import org.github.morotsman.todo.model.unit_of_work.TaskRepository;
import org.github.morotsman.todo.model.unit_of_work.Work;
import org.github.morotsman.todo.model.unit_of_work.WorkRepository;
import org.springframework.stereotype.Repository;

@Repository
public class WorkRepositoryImpl extends GenericHibernateDao<Work,Long> implements WorkRepository {

}
