package com.examples.p2c6;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;

/**
 * Created by ka40215 on 11/1/15.
 */
public class Main {
    public static void main(String[] args) {
//        testGetLoadSave();
        testSetMapping();
    }

    private static void testSetMapping() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Item item = (Item)session.get(Item.class, 1);
        Set itemImages = item.getImages();
        transaction.commit();
        session.close();
    }

    private static void testGetLoadSave() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Item itemGet = (Item)session.get(Item.class, 1);
        Item itemLoad = (Item)session.load(Item.class, 2);

        System.out.println(itemGet.getItemName());
        System.out.println(itemLoad.getItemName());
        
        Item item = new Item();
        item.setItemName("test");
        session.save(item);
        transaction.commit();
        session.close();
    }
}
