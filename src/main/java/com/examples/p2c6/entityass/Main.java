package com.examples.p2c6.entityass;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by ka40215 on 11/12/15.
 */
public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Item item = new Item();
        item.setItemId(1);
        session.save(item);

        Bid bid = new Bid();
        bid.setBidId(1);
        bid.setItem(item);

        Bid bid1 = new Bid();
        bid1.setBidId(2);
        bid1.setItem(item);

        session.save(bid);
        session.save(bid1);

        transaction.commit();
        session.close();
    }
}
