package org.github.morotsman.todo.infrastructure.persistance.hibernate;

import org.github.morotsman.todo.model.sprint.Sprint;
import org.github.morotsman.todo.model.sprint.SprintRepository;
import org.github.morotsman.todo.model.unit_of_work.Story;
import org.github.morotsman.todo.model.unit_of_work.StoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class StoryRepositoryImpl extends GenericHibernateDao<Story,Long> implements StoryRepository {

}
