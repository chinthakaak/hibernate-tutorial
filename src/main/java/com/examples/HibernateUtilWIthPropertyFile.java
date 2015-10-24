package com.examples;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by ka40215 on 10/11/15.
 */
public class HibernateUtilWIthPropertyFile {
    private static final SessionFactory sessionFactory = buildSessionFactoryWithPropertyFile();
    private static final SessionFactory buildSessionFactoryWithPropertyFile() {
        try {
//            HibernateUtilWIthPropertyFile.class.getResourceAsStream()
            return new Configuration().addResource("example1/Message.hbm.xml").addResource("hibernate.properties").buildSessionFactory();
        } catch (Throwable throwable){
            throw new ExceptionInInitializerError(throwable);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
