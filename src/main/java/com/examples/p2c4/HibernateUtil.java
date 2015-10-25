package com.examples.p2c4;

import com.examples.p1c2.MessageAnnotated;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;

/**
 * Created by ka40215 on 10/24/15.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory pragmatically with annotations
            return new AnnotationConfiguration()
                    .setProperty(Environment.DRIVER, "oracle.jdbc.driver.OracleDriver")
                    .setProperty(Environment.URL, "jdbc:oracle:thin:@127.0.0.1:1521:xe")
                    .setProperty(Environment.USER, "hbdb")
                    .setProperty(Environment.PASS, "password")
                    .setProperty(Environment.DIALECT, "org.hibernate.dialect.Oracle10gDialect")
                    .setProperty(Environment.SHOW_SQL, "true")
                    .addResource("p2c4/CategoryFieldAccessXML.hbm.xml")
                    .addResource("p2c4/CategoryPropertyAccessXML.hbm.xml")
                    .buildSessionFactory();

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
