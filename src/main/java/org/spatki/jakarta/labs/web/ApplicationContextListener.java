package org.spatki.jakarta.labs.web;

import org.spatki.jakarta.labs.dao.DaoFactory;
import org.spatki.jakarta.labs.dao.impl.inmemory.*;
import org.spatki.jakarta.labs.services.*;
import java.util.function.UnaryOperator;
import jakarta.servlet.*;
import org.spatki.jakarta.labs.dao.impl.sqlite.SqliteDatabase;
import org.spatki.jakarta.labs.dao.impl.sqlite.SqliteTestData;

public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

//        InMemoryDatabase database = new InMemoryDatabase();
//        
//        InMemoryTestData.generateTo(database);
//
//        DaoFactory daoFactory = database.getDaoFactory();
//
//        TopicService topicService = new TopicServiceImpl(daoFactory);
//        sce.getServletContext().setAttribute("topicService", topicService);
//
//        UserService userService = new UserServiceImpl(daoFactory, UnaryOperator.identity());
//        sce.getServletContext().setAttribute("userService", userService);
        SqliteDatabase database = new SqliteDatabase();

        SqliteTestData.generateTo(database.getEntityManager());

        DaoFactory daoFactory = database.getDaoFactory();

        TopicService topicService = new TopicServiceImpl(daoFactory);
        sce.getServletContext().setAttribute("topicService", topicService);

        UserService userService = new UserServiceImpl(daoFactory, UnaryOperator.identity());
        sce.getServletContext().setAttribute("userService", userService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
