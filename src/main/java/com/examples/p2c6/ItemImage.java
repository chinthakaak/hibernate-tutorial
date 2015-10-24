package com.examples.p2c6;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ka40215 on 10/24/15.
 */
@Entity
@Table
public class ItemImage {
    private String fileName;
    private int imageId;

    private int itemId;
}
