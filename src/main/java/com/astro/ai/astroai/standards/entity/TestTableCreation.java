package com.astro.ai.astroai.standards.entity;

import javax.persistence.*;

@Table
@Entity
public class TestTableCreation {

    @Id
    @GeneratedValue
    private Integer productId;

    @Column
    private String title;

    public TestTableCreation() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TestTableCreation{" +
                "productId=" + productId +
                ", title='" + title + '\'' +
                '}';
    }
}
