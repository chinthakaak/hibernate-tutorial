package com.examples.p2c7;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by ka40215 on 11/14/15.
 *
 * Shared primary key one-to-one associations aren’t uncommon but are relatively
 * rare. In many schemas, a to-one association is represented with a foreign key field
 * and a unique constraint.
 */
public class SingleValuedSharedPrimaryKeyOneToOneAnnotation {
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

        User user2 = new User();
        user2.setUserName("Nethum");
        Address address2 = new Address();
        address2.setCity("Colombo2");
        address2.setStreet("st112");
        address2.setZipcode("121");
        address2.setUser(user2);
        user2.setShippingAddress(address2);

        session.save(user);
        session.save(user2);

        transaction.commit();

//        session.refresh(user);
        User userX = (User)session.load(User.class,1);

        // User to Address direction
        System.out.println(userX.getUserName() +" "+ user.getShippingAddress().getStreet() +" "+ user.getShippingAddress().getCity());


        Address addressX = (Address)session.load(Address.class,1);

        // Address to User direction
        System.out.println(addressX.getCity()+" "+addressX.getUser().getUserName()+" "+addressX.getUser().getId());
        session.close();
    }
    @Entity
    @Table(name = "ADDRESS")
    private static class Address {
        @Id
        @GeneratedValue(generator = "fkGenerator")
        @GenericGenerator(name = "fkGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "user"))
        @Column(name = "ADDRESS_ID")
        private int id;

        @Column(name = "STREET")
        private String street;

        @Column(name = "CITY")
        private String city;

        @Column(name = "ZIPCODE")
        private String zipcode;

        // For association
        @OneToOne
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
        @SequenceGenerator(name = "sequ", sequenceName = "seqannot")
        @GeneratedValue(generator = "sequ")
        @Column(name = "USER_ID")
        private int id;

        @Column(name = "USER_NAME")
        private String userName;

        // for asssociation
        @OneToOne(cascade = {CascadeType.ALL})
        @PrimaryKeyJoinColumn
        private Address shippingAddress;

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
                        .addAnnotatedClass(Address.class)
                        .addAnnotatedClass(User.class)
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
