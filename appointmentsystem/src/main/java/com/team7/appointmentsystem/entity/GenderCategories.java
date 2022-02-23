package com.team7.appointmentsystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "gendercategories")
public class GenderCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String gender;

    public GenderCategories() {
    }

    public GenderCategories(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "GenderCategories{" +
                "id=" + id +
                ", gender='" + gender + '\'' +
                '}';
    }
}
