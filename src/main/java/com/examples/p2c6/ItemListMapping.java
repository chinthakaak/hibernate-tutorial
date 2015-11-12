package com.examples.p2c6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ka40215 on 10/24/15.
 */
public class ItemListMapping {
    private int itemId;
    private String itemName;
    private List images = new ArrayList();

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public List getImages() {
        return images;
    }

    public void setImages(List images) {
        this.images = images;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
