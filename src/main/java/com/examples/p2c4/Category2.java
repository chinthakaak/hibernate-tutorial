package com.examples.p2c4;

import javax.persistence.Id;

/**
 * Created by ka40215 on 10/24/15.
 */
public class Category2 {
    private Long id;

    // hibernate use getter for db writing
    @Id
    public Long getId(){
        return id;
    }

    // setter for db reading
    private void setId(Long id){
        this.id = id;
    }
}
