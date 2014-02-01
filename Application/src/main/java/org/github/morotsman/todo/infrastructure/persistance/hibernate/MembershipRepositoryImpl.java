package org.github.morotsman.todo.infrastructure.persistance.hibernate;

import org.github.morotsman.todo.model.membership.Membership;
import org.github.morotsman.todo.model.membership.MembershipRepository;
import org.github.morotsman.todo.model.user.User;
import org.github.morotsman.todo.model.user.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MembershipRepositoryImpl extends GenericHibernateDao<Membership,Membership.Id> implements MembershipRepository {

}
