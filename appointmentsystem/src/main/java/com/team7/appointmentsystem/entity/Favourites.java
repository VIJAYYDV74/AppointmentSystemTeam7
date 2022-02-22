package com.team7.appointmentsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
@Table(name = "favourites")
public class Favourites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userid")
    private Users users;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "businessid")
    private Business business;

    public Favourites() {
    }

    public Favourites(Users users, Business business) {
        this.users = users;
        this.business = business;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return "Favourites{" +
                "id=" + id +
                ", users=" + users +
                ", business=" + business +
                '}';
    }
}
