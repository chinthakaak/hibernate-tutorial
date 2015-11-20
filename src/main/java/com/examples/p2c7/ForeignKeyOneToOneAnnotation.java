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
 */
public class ForeignKeyOneToOneAnnotation {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user = new User();
        user.setUserName("Kushan");
        Address address = new Address();
        address.setCity("Colombo");
        address.setStreet("st1");
        address.setUser(user);
        user.setShippingAddress(address);

        Address homeAddress = new Address();
        homeAddress.setCity("Kottawa");
        homeAddress.setStreet("233");
        homeAddress.setZipcode("111");
        homeAddress.setUser(user);
        user.setHomeAddress(homeAddress);

        Address billingAddress = new Address();
        billingAddress.setCity("Bil");
        billingAddress.setStreet("ssss");
        billingAddress.setZipcode("sdsds");
        billingAddress.setUser(user);
        user.setBillingAddress(billingAddress);

        User user2 = new User();
        user2.setUserName("Nethum");
        Address address2 = new Address();
        address2.setCity("Colombo2");
        address2.setStreet("st112");
        address2.setZipcode("121");
        address2.setUser(user2);
        user2.setShippingAddress(address2);

        Address homeAddress2 = new Address();
        homeAddress2.setCity("2Kottawa");
        homeAddress2.setStreet("2233");
        homeAddress2.setZipcode("2111");
        homeAddress2.setUser(user2);
        user2.setHomeAddress(homeAddress2);

        Address billingAddress2 = new Address();
        billingAddress2.setCity("2Bil");
        billingAddress2.setStreet("2ssss");
        billingAddress2.setZipcode("2sdsds");
        billingAddress2.setUser(user2);
        user2.setBillingAddress(billingAddress2);

        session.save(user);
        session.save(user2);

        transaction.commit();

        session.close();

        Session session1 = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction1 = session1.beginTransaction();

        // reverse dirrection check
        Address anyAddress = (Address) session1.get(Address.class, 2);
        System.out.println(anyAddress.getUser().getHomeAddress().getCity());
        System.out.println(anyAddress.getUser().getBillingAddress().getCity());
        System.out.println(anyAddress.getUser().getShippingAddress().getCity());

        Address anyAddress1 = (Address) session1.get(Address.class, 7);
        System.out.println(anyAddress1.getUser().getHomeAddress().getCity());
        System.out.println(anyAddress1.getUser().getBillingAddress().getCity());
        System.out.println(anyAddress1.getUser().getShippingAddress().getCity());

        Address anyAddress2 = (Address) session1.get(Address.class, 4);
        System.out.println(anyAddress2.getUser().getHomeAddress().getCity());
        System.out.println(anyAddress2.getUser().getBillingAddress().getCity());
        System.out.println(anyAddress2.getUser().getShippingAddress().getCity());

        transaction1.commit();

        session1.close();

    }
    @Entity
    @Table(name = "ADDRESS")
    private static class Address {
        @Id
        @SequenceGenerator(name = "sequence1", sequenceName = "seqq")
        @GeneratedValue(generator = "sequence1")
        @Column(name = "ADDRESS_ID")
        private int id;

        @Column(name = "STREET")
        private String street;

        @Column(name = "CITY")
        private String city;

        @Column(name = "ZIPCODE")
        private String zipcode;

        @OneToOne//(mappedBy = "billingAddress")
        private User user;

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
        @SequenceGenerator(name = "sequence2", sequenceName = "seqq")
        @GeneratedValue(generator = "sequence2")
        @Column(name = "USER_ID")
        private int id;

        @Column(name = "USER_NAME")
        private String userName;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "SHIPPING_ADDRESS")
        private Address shippingAddress;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "HOME_ADDRESS")
        private Address homeAddress;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "BILLING_ADDRESS")
        private Address billingAddress;

        public Address getHomeAddress() {
            return homeAddress;
        }

        public void setHomeAddress(Address homeAddress) {
            this.homeAddress = homeAddress;
        }

        public Address getBillingAddress() {
            return billingAddress;
        }

        public void setBillingAddress(Address billingAddress) {
            this.billingAddress = billingAddress;
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
                        .setProperty(Environment.HBM2DDL_AUTO, "create")
//                        .setProperty(Environment.HBM2DDL_AUTO, "update")
                        .addAnnotatedClass(User.class)
                        .addAnnotatedClass(Address.class)
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
