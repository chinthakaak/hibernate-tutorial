package com.examples.p2c4;

/**
 * Created by ka40215 on 10/24/15.
 */
public class CategoryPropertyAccessXML {
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
