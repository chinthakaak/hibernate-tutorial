package com.examples.p2c6.annot;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.IndexColumn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ka40215 on 11/12/15.
 */
@Entity
@Table(name = "ITEMA_LIST_MAPPING")
public class ItemListMapping {
    @SequenceGenerator(name = "sequence", sequenceName = "seq")
    @GeneratedValue(generator = "sequence")
    @Id
    @Column(name = "ITEM_ID")
    private int itemId;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @CollectionOfElements
    @JoinTable(name = "ITEMA_IMAGE_LIST_MAPPING", joinColumns = @JoinColumn(name = "ITEM_ID"))
    @IndexColumn(name = "POSITION", base = 1)
    @Column(name = "FILENAME")
    private List<String> images = new ArrayList<String>();

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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
