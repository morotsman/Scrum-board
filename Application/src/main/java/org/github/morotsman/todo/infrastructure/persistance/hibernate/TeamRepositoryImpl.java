package org.github.morotsman.todo.infrastructure.persistance.hibernate;

import org.github.morotsman.todo.model.team.TeamRepository;
import org.github.morotsman.todo.model.team.Team;
import org.springframework.stereotype.Repository;

@Repository
public class TeamRepositoryImpl extends GenericHibernateDao<Team,Long> implements TeamRepository {

}
