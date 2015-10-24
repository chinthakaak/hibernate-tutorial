package com.examples.p2c6;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ka40215 on 10/24/15.
 */
@Entity
@Table
public class Item {
    @Id
    private int itemId;
    private String itemName;

    private Set images = new HashSet();

    public Set getImages() {
        return images;
    }

    public void setImages(Set images) {
        this.images = images;
    }
}
