package com.team7.appointmentsystem.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long likeid;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "likedby")
    private Users users;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "likedto")
    private Business business;

    public Likes() {
    }

    public Likes(Users users, Business business) {
        this.users = users;
        this.business = business;
    }

    public long getLikeid() {
        return likeid;
    }

    public void setLikeid(long likeid) {
        this.likeid = likeid;
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
}
