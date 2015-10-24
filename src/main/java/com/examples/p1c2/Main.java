package com.examples.p1c2;

import com.examples.p1c2.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by ka40215 on 10/24/15.
 */
public class Main {
    public static void main(String[] args) {
        testXmlCfg();

        testProgCfg();

        testAnnoCfg();

        testAnnoCfgNoXML();

    }

    private static void testAnnoCfgNoXML() {
        Session session = HibernateAnnotationCfgUtilNoXML.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        MessageAnnotated message = new MessageAnnotated("Test");

        Long msgId =(Long) session.save(message);
        transaction.commit();
        session.close();
        HibernateProgramaticCfgUtil.shutdown();
    }

    private static void testAnnoCfg() {
        Session session = HibernateAnnotationCfgUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        MessageAnnotated message = new MessageAnnotated("Test");

        Long msgId =(Long) session.save(message);
        transaction.commit();
        session.close();
        HibernateProgramaticCfgUtil.shutdown();
    }

    private static void testProgCfg() {
        Session session = HibernateProgramaticCfgUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Message message = new Message("Test");
        Long msgId =(Long) session.save(message);
        transaction.commit();
        session.close();
        HibernateProgramaticCfgUtil.shutdown();
    }

    private static void testXmlCfg() {
        Session session = HibernateXmlCfgUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Message message = new Message("Test");
        Long msgId =(Long) session.save(message);
        transaction.commit();
        session.close();
        HibernateXmlCfgUtil.shutdown();
    }
}
