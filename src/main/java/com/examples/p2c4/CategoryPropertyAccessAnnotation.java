package com.examples.p2c4;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ka40215 on 10/24/15.
 */
@Entity
@Table(name = "Category")
public class CategoryPropertyAccessAnnotation {
    private Long id;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @Id
    @Column(name = "CATEGORY_ID")
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
}
