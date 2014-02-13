package org.github.morotsman.todo.config;


import java.util.Properties;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.github.morotsman.todo.model.membership.Membership;
import org.github.morotsman.todo.model.sprint.Sprint;
import org.github.morotsman.todo.model.team.Team;
import org.github.morotsman.todo.model.unit_of_work.Story;
import org.github.morotsman.todo.model.unit_of_work.Task;
import org.github.morotsman.todo.model.user.User;
import org.github.morotsman.todo.model.user.UserRepository;
import org.github.morotsman.todo.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {



    @Bean(name="sessionFactory")
    public SessionFactory sessionFactory() {

        Properties properties = new Properties();
        properties.setProperty("hibernate.format_sql","true");
        properties.setProperty("hibernate.show_sql","true");
        properties.setProperty("hibernate.hbm2ddl.auto","create");
        //properties.setProperty("hibernate.hbm2ddl.import_files","/import.sql");

        return new LocalSessionFactoryBuilder(datasource())
                .addAnnotatedClasses(User.class, Team.class, Membership.class, Sprint.class, Task.class, Story.class)
                .addProperties(properties)
                .buildSessionFactory() ;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory());
    }

    @Bean
    public DataSource datasource() {
        EmbeddedDatabaseFactoryBean bean = new EmbeddedDatabaseFactoryBean();
        bean.setDatabaseType(EmbeddedDatabaseType.H2);
        //ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        //databasePopulator.addScript(new ClassPathResource("import.sql"));
        //bean.setDatabasePopulator(databasePopulator);
        bean.afterPropertiesSet(); // necessary because EmbeddedDatabaseFactoryBean is a FactoryBean
        return bean.getObject();
    }


}
