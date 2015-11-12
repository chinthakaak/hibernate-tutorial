package com.examples.p2c6;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ka40215 on 11/1/15.
 */
public class Main {
    public static void main(String[] args) {
//        testGetLoadSave();
//        testSetMapping();
//        testIdbagMapping();
        testBagMapping();
    }


    private static void testBagMapping() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        ItemDuplicatesBag itemDuplicatesBag = new ItemDuplicatesBag();
        itemDuplicatesBag.setItemName("book");

        Collection collection = new ArrayList();
        collection.add("b1.png");
        collection.add("b2.png");
        collection.add("b2.png");

        itemDuplicatesBag.setImages(collection);
        session.save(itemDuplicatesBag);
        transaction.commit();
        session.close();
    }

    private static void testIdbagMapping() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        ItemDuplicatesIdbag itemDuplicatesIdbag = new ItemDuplicatesIdbag();
        itemDuplicatesIdbag.setItemName("book");

        Collection collection = new ArrayList();
        collection.add("b1.png");
        collection.add("b2.png");
        collection.add("b2.png");

        itemDuplicatesIdbag.setImages(collection);
        session.save(itemDuplicatesIdbag);
        transaction.commit();
        session.close();
    }

    private static void testSetMapping() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Item item = (Item) session.get(Item.class, 1);
        Set<String> itemImages = item.getImages();
        for (String img: itemImages) System.out.println(img);

        Set imageSet= new HashSet();
        imageSet.add("image1.png");
        imageSet.add("image2.png");

        Item item1 = new Item();
        item1.setItemName("phone");
        item1.setImages(imageSet);

        session.save(item1);

        transaction.commit();
        session.close();

        HibernateUtil.shutdown();
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
