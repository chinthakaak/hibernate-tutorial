package com.examples;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by ka40215 on 10/11/15.
 */
public class HibernatePropertyFile {
    public static void main(String[] args) {
        Session session = HibernateUtilWIthPropertyFile.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Message message = new Message("test");
        Long messageId = (Long) session.save(message);
        transaction.commit();
        session.close();
        HibernateUtilWIthPropertyFile.shutdown();
    }
}
