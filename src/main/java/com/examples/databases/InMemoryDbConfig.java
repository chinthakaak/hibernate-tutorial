package com.examples.databases;

import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Created by ka40215 on 9/20/15.
 */
public class InMemoryDbConfig {
    public static AnnotationConfiguration configuration(Class... classes) {
        long timeStamp = System.currentTimeMillis();
        AnnotationConfiguration configuration = (new AnnotationConfiguration())
                .setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
                .setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver")
                .setProperty("hibernate.connection.url", "jdbc:hsqldb:mem:" + timeStamp)
                .setProperty("hibernate.connection.username", "sa")
                .setProperty("hibernate.connection.password", "")
                .setProperty("hibernate.connection.pool_size", "1")
                .setProperty("hibernate.connection.autocommit", "true")
                .setProperty("hibernate.cache.provider_class", "org.hibernate.cache.HashtableCacheProvider")
                .setProperty("hibernate.hbm2ddl.auto", "create-drop")
                .setProperty("hibernate.show_sql", "true");
        Class[] arr$ = classes;
        int len$ = classes.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Class clazz = arr$[i$];
            configuration.addAnnotatedClass(clazz);
        }

        return configuration;
    }
    private InMemoryDbConfig() {
    }
}
