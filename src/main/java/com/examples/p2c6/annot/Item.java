package com.examples.p2c6.annot;

import org.hibernate.annotations.CollectionOfElements;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ka40215 on 11/12/15.
 */
@Entity
@Table(name = "ITEMA")
public class Item {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "seq")
    @GeneratedValue(generator = "sequence")
    @Column(name = "ITEM_ID")
    private int itemId;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @CollectionOfElements
    @JoinTable(name = "ITEMA_IMAGE", joinColumns = @JoinColumn(name = "ITEM_ID"))
    @Column(name = "FILENAME", nullable = false)
    private Set<String> images = new HashSet();

    public Set getImages() {
        return images;
    }

    public void setImages(Set images) {
        this.images = images;
    }

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
