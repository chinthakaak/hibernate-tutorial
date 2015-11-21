package com.examples.p2c7;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by ka40215 on 11/14/15.
 * 
 * Shared primary key one-to-one associations aren’t uncommon but are relatively
 * rare. In many schemas, a to-one association is represented with a foreign key field
 * and a unique constraint.
 */
public class JoinTableForeignKeyOneToOneAnnotation {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Item item = new Item();
        item.setItemName("Sample");

        Shipment shipment = new Shipment();
        shipment.setShipmentType("AIR");

        shipment.setAuction(item);

        session.save(shipment);

        transaction.commit();
        session.close();

        Session session1 = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction1 = session1.beginTransaction();

        Shipment shipment1 = (Shipment) session1.get(Shipment.class, 1);
        System.out.println(shipment1.getShipmentType());
        System.out.println(shipment1.getAuction().getItemName());

        Item item1 = (Item) session1.get(Item.class, 2);
        System.out.println(item1.getItemName());
//        System.out.println(item1.getShipment().getShipmentType());

        transaction1.commit();
        session1.close();
    }
    @Entity
    @Table(name = "ITEM")
//    @SecondaryTable(name = "SHIPMENT_ITEM")
    private static class Item {
        @Id
        @SequenceGenerator(name = "seq", sequenceName = "seee")
        @GeneratedValue(generator = "seq")
        @Column(name = "ITEM_ID")
        private int itemId;

        @Column(name = "ITEM_NAME")
        private String itemName;

//        @OneToOne
//        @JoinColumn(table = "SHIPMENT_ITEM", )
//        private Shipment shipment;

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

//        public Shipment getShipment() {
//            return shipment;
//        }
//
//        public void setShipment(Shipment shipment) {
//            this.shipment = shipment;
//        }
    }

    @Entity
    @Table(name = "SHIPMENT")
    private static class Shipment {
        @Id
        @SequenceGenerator(name = "seq", sequenceName = "seee")
        @GeneratedValue(generator = "seq")
        @Column(name = "SHIPMENT_ID")
        private int shipmentId;

        @Column(name = "SHIPMENT_TYPE")
        private String shipmentType;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinTable(
                name = "SHIPMENT_ITEM",
                joinColumns = @JoinColumn(name = "SHIPMENT_ID"),
                inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
                )
        private Item auction;

        public Item getAuction() {
            return auction;
        }

        public void setAuction(Item auction) {
            this.auction = auction;
        }

        public int getShipmentId() {
            return shipmentId;
        }

        public void setShipmentId(int shipmentId) {
            this.shipmentId = shipmentId;
        }

        public String getShipmentType() {
            return shipmentType;
        }

        public void setShipmentType(String shipmentType) {
            this.shipmentType = shipmentType;
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
                        .setProperty(Environment.HBM2DDL_AUTO, "create")
//                        .setProperty(Environment.HBM2DDL_AUTO, "update")
                        .addAnnotatedClass(Item.class)
                        .addAnnotatedClass(Shipment.class)
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
