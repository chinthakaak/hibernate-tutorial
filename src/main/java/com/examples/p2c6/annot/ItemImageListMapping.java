package com.examples.p2c6.annot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by ka40215 on 11/12/15.
 */
@Entity
@Table(name = "ITEMA_IMAGE_LIST_MAPPING")
public class ItemImageListMapping {

    @Id
    @Column(name = "ITEM_ID")
    private int itemId;

    @Column(name = "POSITION")
    private int position;

    @Column(name = "FILENAME")
    private String fileName;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
