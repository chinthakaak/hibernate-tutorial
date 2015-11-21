package com.examples.p2c7;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;

/**
 * Created by ka40215 on 11/14/15.
 * 
 * Shared primary key one-to-one associations aren’t uncommon but are relatively
 * rare. In many schemas, a to-one association is represented with a foreign key field
 * and a unique constraint.
 */
public class JoinTableForeignKeyOneToOneXML {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Item item = new Item();
        item.setItemName("Sample");

        Shipment shipment = new Shipment();
        shipment.setShipmentType("AIR");

        shipment.setItem(item);

        session.save(shipment);

        transaction.commit();
        session.close();

        Session session1 = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction1 = session1.beginTransaction();

        Shipment shipment1 = (Shipment) session1.get(Shipment.class, 1);
        System.out.println(shipment1.getShipmentType());

        Item item1 = (Item) session1.get(Item.class, 2);
        System.out.println(item1.getItemName());

        transaction1.commit();
        session1.close();
    }
    private static class Item {
        private int itemId;
        private String itemName;
        private Shipment shipment;

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

        public Shipment getShipment() {
            return shipment;
        }

        public void setShipment(Shipment shipment) {
            this.shipment = shipment;
        }
    }

    private static class Shipment {
        private int shipmentId;
        private String shipmentType;
        private Item item;

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

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
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
                        .addResource("p2c7/JoinTableForeignKeyOneToOneXML.hbm.xml")
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
