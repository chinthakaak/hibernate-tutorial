package com.examples.p2c6.annot;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ka40215 on 11/12/15.
 */
public class Main {
    public static void main(String[] args) {
        testSetMapping();
    }

    private static void testSetMapping() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Item item = new Item();
        item.setItemName("transistors");

        Set images = new HashSet();
        images.add("t1.png");
        images.add("t2.png");
        images.add("t3.png");

        item.setImages(images);

        session.save(item);

        images.add("t4.png");

        Item item1 = (Item)session.load(Item.class,1);

        Set<String> images1 = item1.getImages();

        for(String img: images1) System.out.println(img);

        transaction.commit();
        session.close();
    }
}
