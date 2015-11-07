package com.examples.p2c6;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ka40215 on 10/24/15.
 */
public class ItemImage {
    private String fileName;

    private int itemId;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
