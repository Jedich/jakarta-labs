package org.spatki.labs.web;

import jakarta.servlet.*;

public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        SqliteTestData.generateTo();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
