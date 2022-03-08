package com.team7.appointmentsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Categories {

    @Id
    @Column(name = "categoryid")
    private int categoryid;

    @Column(columnDefinition = "varchar(255)", name = "categoryname")
    private String categoryName;

    @Column(name = "isblocked", columnDefinition = "boolean default false")
    private boolean isBlocked;

    public Categories() {

    }

    public Categories(int categoryid) {
        this.categoryid = categoryid;
    }

    public Categories(int categoryid, String categoryName) {
        this.categoryid = categoryid;
        this.categoryName = categoryName;
    }

    public Categories(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryid;
    }

    public void setCategoryId(int categoryId) {
        this.categoryid = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "categoryId=" + categoryid +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
