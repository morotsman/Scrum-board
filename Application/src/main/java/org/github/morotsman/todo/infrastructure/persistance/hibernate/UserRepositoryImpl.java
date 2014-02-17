package org.github.morotsman.todo.infrastructure.persistance.hibernate;

import org.github.morotsman.todo.model.user.User;
import org.github.morotsman.todo.model.user.UserRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl extends GenericHibernateDao<User,Long> implements UserRepository {

    @Override
    public List<User> findUserLike(User exampleInstance, String... excludeProperties) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        Example example = Example.create(exampleInstance).enableLike(MatchMode.ANYWHERE).ignoreCase();
        for(String exclude: excludeProperties){
            example.excludeProperty(exclude);
        }
        criteria.add(example);
        return criteria.list();
    }
}
