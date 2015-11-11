package com.examples.p2c6;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ka40215 on 11/10/15.
 */
public class ItemDuplicatesIdbag {
    private int itemId;
    private String itemName;
    private Collection images = new ArrayList();

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

    public Collection getImages() {
        return images;
    }

    public void setImages(Collection images) {
        this.images = images;
    }
}
