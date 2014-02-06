package org.github.morotsman.todo.model.unit_of_work;

import org.github.morotsman.todo.config.HibernateConfig;
import org.github.morotsman.todo.model.team.Team;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfig.class)
public class WorkTest {


    @Resource
    private SessionFactory sessionFactory;

    @Test
    public void createStory(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Team team = getTeam("aTeam");
        session.save(team);

        Story story = getStory();
        story.addTeam(team);
        session.save(story);
        tx.commit();
        session.close();
    }

    private Team getTeam(String teamName){
        Team result = new Team();
        result.setDescription("A fine team");
        result.setName(teamName);
        return result;
    }

    private Story getStory() {
        Story story = new Story();
        story.setBug(false);
        story.setDescription("As a user...");
        story.setName("Persist Story");
        story.setEstimate(5L);
        return story;
    }

    @Test
    public void createTask(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Team team = getTeam("cTeam");
        session.save(team);

        Story story = getStory();
        story.setTeam(team);
        session.save(story);

        Task task = getTask(story);
        session.save(task);

        tx.commit();
        session.close();
    }

    private Task getTask(Story parent) {
        Task task = new Task();
        task.setDescription("Create task");
        task.setName("Create task");
        task.setEstimate(5L);
        if(parent != null){
            task.setStory(parent);
        }
        return task;
    }





}
