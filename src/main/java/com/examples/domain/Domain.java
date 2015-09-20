package com.examples.domain;

import javax.persistence.*;

/**
 * Created by ka40215 on 9/20/15.
 */
@Entity
@Table(name = "DOMAIN")
public class Domain {

    private Long id;
    private String column1;

    @Column(name = "column1")
    public String getColumn1() {
        return column1;
    }


    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}