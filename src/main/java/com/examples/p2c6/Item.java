package com.examples.p2c6;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ka40215 on 10/24/15.
 */
public class Item {
    private int itemId;
    private String itemName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    private Set images = new HashSet();

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
}
