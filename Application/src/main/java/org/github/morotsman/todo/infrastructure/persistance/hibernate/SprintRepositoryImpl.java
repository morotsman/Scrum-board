package org.github.morotsman.todo.infrastructure.persistance.hibernate;

import org.github.morotsman.todo.model.sprint.Sprint;
import org.github.morotsman.todo.model.sprint.SprintRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SprintRepositoryImpl extends GenericHibernateDao<Sprint,Long> implements SprintRepository {

}
