package com.examples.p2c6.annot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ka40215 on 11/12/15.
 */
@Entity
@Table(name = "ITEMA_IMAGE")
public class ItemImage {
    @Id
    @Column(name = "ITEM_NAME")
    private int itemId;

    @Column(name = "FILENAME")
    private String fileName;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
