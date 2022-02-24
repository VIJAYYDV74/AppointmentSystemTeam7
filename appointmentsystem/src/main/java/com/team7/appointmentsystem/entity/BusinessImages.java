package com.team7.appointmentsystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class BusinessImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "businessid")
    private Business business;

    @Column(name = "imagepath")
    private String imagePath;

    public BusinessImages() {
    }

    public BusinessImages(Business business, String imagePath) {
        this.business = business;
        this.imagePath = imagePath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "BusinessImages{" +
                "id=" + id +
                ", business=" + business +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
