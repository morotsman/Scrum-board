package org.github.morotsman.todo.infrastructure.persistance.hibernate;

import org.github.morotsman.todo.model.user.User;
import org.github.morotsman.todo.model.user.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends GenericHibernateDao<User,Long> implements UserRepository {

}
