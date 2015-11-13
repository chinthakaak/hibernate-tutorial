package com.examples.p2c6.entityass;

/**
 * Created by ka40215 on 11/12/15.
 */
public class Bid {
    private int bidId;
    private Item item;

    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

}
