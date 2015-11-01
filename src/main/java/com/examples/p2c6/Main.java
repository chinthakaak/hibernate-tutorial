package com.examples.p2c6;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ka40215 on 11/1/15.
 */
public class Main {
    public static void main(String[] args) {
        testSet();
    }

    private static void testSet() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Item item = new Item();

        Set images = new HashSet();
//        images.
        item.setImages(images);

        ItemImage itemImage = new ItemImage();
//        session.
    }
}
