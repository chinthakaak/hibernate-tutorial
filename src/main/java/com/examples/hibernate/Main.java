package com.examples.hibernate;

import com.examples.databases.InMemoryDbConfig;
import com.examples.domain.Domain;
import org.hibernate.cfg.Configuration;


/**
 * Created by ka40215 on 9/20/15.
 */
public class Main {
    public static void main(String[] args) {
        Configuration configuration= InMemoryDbConfig.configuration(Domain.class);
    }
}
