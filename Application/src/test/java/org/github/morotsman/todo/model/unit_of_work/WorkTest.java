package org.github.morotsman.todo.model.unit_of_work;

import org.github.morotsman.todo.config.HibernateConfig;
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

        Story story = getStory();
        Long messageId = (Long)session.save(story);
        tx.commit();
        session.close();
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

        Task task = getTask(null);
        Long messageId = (Long)session.save(task);
        tx.commit();
        session.close();
    }

    private Task getTask(Work parent) {
        Task task = new Task();
        task.setDescription("Create task");
        task.setName("Create task");
        task.setEstimate(5L);
        if(parent != null){
            task.setParent(parent);
        }
        return task;
    }

    @Test
    public void addTaskToStory(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Story story = getStory();
        session.save(story);

        Task task = getTask(story);
        session.save(task);

        tx.commit();
        session.close();
    }

    @Test
    public void countTasks(){
        Session session1 = sessionFactory.openSession();
        Transaction tx1 = session1.beginTransaction();

        Story story = getStory();
        Long id = (Long)session1.save(story);

        Task task = getTask(story);
        session1.save(task);

        task = getTask(story);
        session1.save(task);

        tx1.commit();
        session1.close();

        Session session2 = sessionFactory.openSession();
        Transaction tx2 = session2.beginTransaction();

        Work storyWithTasks = (Work)session2.load(Story.class, id);

        Long expected = 2L;
        Assert.assertEquals("Expected a different number of tasks.", expected,storyWithTasks.numberOfTasks());

        tx2.commit();
        session2.close();
    }



}
