package com.examples.p2c6;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by ka40215 on 10/24/15.
 */
public class CollectionMappingExamples {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

    }
}
