package com.examples.p2c4;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ka40215 on 10/25/15.
 */
@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    private Long id;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getId(){
        return id;
    }

    private void setId(Long id){
        this.id = id;
    }
}
