package com.examples.p2c6.entityass;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by ka40215 on 11/13/15.
 */
public class BidItemAnnotation {
    public static void main(String[] args) {
        testBid();
    }

    private static void testBid() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Item item = new Item();
        item.setItemName("Resistor"+Math.random());

        Bid bid1 = new Bid();
        bid1.setBidAmount(100);
        bid1.setItem(item);

        Bid bid2 = new Bid();
        bid2.setBidAmount(99);
        bid2.setItem(item);

        session.save(item);
        session.save(bid1);
        session.save(bid2);

        transaction.commit();
        session.close();
    }


    @Entity
    @Table(name = "BID")
    private static class Bid {
        @SequenceGenerator(name = "bid_sequence", sequenceName = "b_seq")
        @GeneratedValue(generator = "bid_sequence")
        @Id
        @Column(name = "BID_ID")
        private int bidId;

        @Column(name = "BID_AMOUNT")
        private int bidAmount;

        @ManyToOne(targetEntity = Item.class)
        @JoinColumn(name = "ITEM_ID", nullable = false)
        private Item item;

        public int getBidId() {
            return bidId;
        }

        public void setBidId(int bidId) {
            this.bidId = bidId;
        }

        public int getBidAmount() {
            return bidAmount;
        }

        public void setBidAmount(int bidAmount) {
            this.bidAmount = bidAmount;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }
    }

    @Entity
    @Table(name = "ITEM")
    private static class Item {
        @SequenceGenerator(name = "item_sequence", sequenceName = "i_seq")
        @GeneratedValue(generator = "item_sequence")
        @Id
        @Column(name = "ITEM_ID")
        private int itemId;

        @Column(name = "ITEM_NAME", unique = true)
        private String itemName;

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }
    }

    private static class HibernateUtil {
        private static final SessionFactory sessionFactory = buildSessionFactory();

        private static SessionFactory buildSessionFactory() {
            try {
                // Create the SessionFactory programmatically
                return new AnnotationConfiguration()
                        .setProperty(Environment.DRIVER, "oracle.jdbc.driver.OracleDriver")
                        .setProperty(Environment.URL, "jdbc:oracle:thin:@127.0.0.1:1521:xe")
                        .setProperty(Environment.USER, "TESTDB")
                        .setProperty(Environment.PASS, "password")
                        .setProperty(Environment.DIALECT, "org.hibernate.dialect.Oracle10gDialect")
                        .setProperty(Environment.SHOW_SQL, "true")
//                        .setProperty(Environment.HBM2DDL_AUTO,"create")
                        .setProperty(Environment.HBM2DDL_AUTO, "update")
                        .addAnnotatedClass(Bid.class)
                        .addAnnotatedClass(Item.class)
                        .buildSessionFactory();

            } catch (Throwable ex) {
                // Make sure you log the exception, as it might be swallowed
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }

        public static SessionFactory getSessionFactory() {
            return sessionFactory;
        }

        public static void shutdown() {
            // Close caches and connection pools
            getSessionFactory().close();
        }
    }
}
