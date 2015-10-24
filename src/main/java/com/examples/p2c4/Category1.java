package com.examples.p2c4;

import javax.persistence.Id;

/**
 * Created by ka40215 on 10/24/15.
 */
public class Category1 {
    @Id
    private Long id;

    public Long getId(){
        return id;
    }

    private void setId(Long id){
        this.id = id;
    }
}
