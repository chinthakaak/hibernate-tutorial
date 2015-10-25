package com.examples.p2c4;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by ka40215 on 10/25/15.
 */
public class Main {
    public static void main(String[] args) {
        testFieldXML();
        testPropertyXML();
        testFieldAnnot();
        testPropertyAnnot();
    }

    private static void testFieldAnnot() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CategoryFieldAccessAnnotation categoryFieldAccessAnnotation = new CategoryFieldAccessAnnotation();
        categoryFieldAccessAnnotation.setId(2L);
        categoryFieldAccessAnnotation.setCategory("Sample");
        session.save(categoryFieldAccessAnnotation);
        transaction.commit();
        session.close();
        HibernateUtil.shutdown();
    }

    private static void testPropertyAnnot() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CategoryPropertyAccessAnnotation categoryPropertyAccessAnnotation = new CategoryPropertyAccessAnnotation();
        categoryPropertyAccessAnnotation.setId(1L);
        categoryPropertyAccessAnnotation.setCategory("Sample");
        session.save(categoryPropertyAccessAnnotation);
        transaction.commit();
        session.close();
        HibernateUtil.shutdown();
        
    }

    private static void testPropertyXML() {
        //in hbm default-access="property"
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CategoryFieldAccessXML categoryFieldAccessXML = new CategoryFieldAccessXML();
        categoryFieldAccessXML.setCategory("Sample");
        session.save(categoryFieldAccessXML);
        transaction.commit();
        session.close();
        HibernateUtil.shutdown();
    }

    private static void testFieldXML() {
        //in hbm default-access="field"
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CategoryFieldAccessXML categoryFieldAccessXML = new CategoryFieldAccessXML();
        categoryFieldAccessXML.setCategory("Sample");
        session.save(categoryFieldAccessXML);
        transaction.commit();
        session.close();
        HibernateUtil.shutdown();
    }
}
