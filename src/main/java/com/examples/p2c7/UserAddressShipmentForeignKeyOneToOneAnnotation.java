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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by ka40215 on 11/14/15.
 * 
 * Shared primary key one-to-one associations aren’t uncommon but are relatively
 * rare. In many schemas, a to-one association is represented with a foreign key field
 * and a unique constraint.
 *
 * THIS IS AN INVALID EXAMPLE - all the addresses should be in user only
 */
public class UserAddressShipmentForeignKeyOneToOneAnnotation {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user = new User();
        user.setUserName("Kushan");
        Address homeAddress = new Address();
        homeAddress.setCity("Kottawa");
        homeAddress.setStreet("233");
        homeAddress.setZipcode("111");
        homeAddress.setUser(user);
        user.setHomeAddress(homeAddress);


        Shipment shipment = new Shipment();
        shipment.setVessel("CAR");
        Address shipmentAddress = new Address();
        shipmentAddress.setCity("Colombo2");
        shipmentAddress.setStreet("st112");
        shipmentAddress.setZipcode("121");
        shipmentAddress.setShipment(shipment);
        shipment.setShippingAddress(shipmentAddress);

        session.save(user);
        session.save(shipment);

        transaction.commit();

        session.close();

        Session session1 = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction1 = session1.beginTransaction();


        transaction1.commit();

        session1.close();

    }
    @Entity
    @Table(name = "ADDRESS")
    private static class Address {
        @Id
        @SequenceGenerator(name = "sequence", sequenceName = "seq_common")
        @GeneratedValue(generator = "sequence")
        @Column(name = "ADDRESS_ID")
        private int id;

        @Column(name = "STREET")
        private String street;

        @Column(name = "CITY")
        private String city;

        @Column(name = "ZIPCODE")
        private String zipcode;

        @OneToOne(mappedBy = "homeAddress")
        private User user;

        @OneToOne(mappedBy = "shippingAddress")
        private Shipment shipment;

        public Shipment getShipment() {
            return shipment;
        }

        public void setShipment(Shipment shipment) {
            this.shipment = shipment;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

    @Entity
    @Table(name = "USERS")
    private static class User {
        @Id
        @SequenceGenerator(name = "sequence", sequenceName = "seq_common")
        @GeneratedValue(generator = "sequence")
        @Column(name = "USER_ID")
        private int id;

        @Column(name = "USER_NAME")
        private String userName;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "HOME_ADDRESS")
        private Address homeAddress;

        public Address getHomeAddress() {
            return homeAddress;
        }

        public void setHomeAddress(Address homeAddress) {
            this.homeAddress = homeAddress;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    @Entity
    @Table(name = "SHIPMENT")
    private static class Shipment {
        @Id
        @SequenceGenerator(name = "sequence", sequenceName = "seq_common")
        @GeneratedValue(generator = "sequence")
        @Column(name = "SHIPMENT_ID")
        private int id;

        @Column(name = "VESSEL")
        private String vessel;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "SHIPPING_ADDRESS")
        private Address shippingAddress;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVessel() {
            return vessel;
        }

        public void setVessel(String vessel) {
            this.vessel = vessel;
        }

        public Address getShippingAddress() {
            return shippingAddress;
        }

        public void setShippingAddress(Address shippingAddress) {
            this.shippingAddress = shippingAddress;
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
//                        .setProperty(Environment.HBM2DDL_AUTO, "create")
                        .setProperty(Environment.HBM2DDL_AUTO, "update")
                        .addAnnotatedClass(User.class)
                        .addAnnotatedClass(Address.class)
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
