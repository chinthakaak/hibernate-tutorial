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
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ka40215 on 11/22/15.
 */
public class UnidirectionalManyToManyAnnotation {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Category category = new Category();
        category.setCategoryName("Phones");

        Item item = new Item();
        item.setItemName("ka40");
        Item item2 = new Item();
        item2.setItemName("ka4");
        Item item3 = new Item();
        item3.setItemName("ka433");
        Set<Item> items = new HashSet<Item>();
        items.add(item);
        items.add(item2);
        items.add(item3);
        category.setItems(items);
        session.save(category);

        transaction.commit();
        session.close();

    }

    @Entity
    @Table(name = "CATEGORY")
    private static class Category {
        @Id
        @SequenceGenerator(name = "seq", sequenceName = "se")
        @GeneratedValue(generator = "seq")
        private int categoryId;

        @Column(name = "CATEGORY_NAME")
        private String categoryName;

        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(name = "CATEGORY_ITEM",
                    joinColumns = @JoinColumn(name = "CATEGORY_ID"),
                    inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
                    )
        private Set<Item> items;

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public Set<Item> getItems() {
            return items;
        }

        public void setItems(Set<Item> items) {
            this.items = items;
        }
    }

    @Entity
    @Table(name = "ITEM")
    private static class Item {
        @Id
        @SequenceGenerator(name = "seq", sequenceName = "se")
        @GeneratedValue(generator = "seq")
        private int itemId;

        @Column(name = "ITEM_NAME")
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
                        .setProperty(Environment.HBM2DDL_AUTO, "create")
//                        .setProperty(Environment.HBM2DDL_AUTO, "update")
                        .addAnnotatedClass(Category.class)
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
