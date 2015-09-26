package com.examples;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by ka40215 on 9/26/15.
 */
public class HelloWorld {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Message message = new Message("Hello World");
        Long msgId =(Long) session.save(message);
        transaction.commit();
        session.close();
        HibernateUtil.shutdown();
    }
}
