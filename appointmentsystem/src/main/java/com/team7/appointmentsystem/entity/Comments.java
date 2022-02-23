package com.team7.appointmentsystem.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentid;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "commentedto")
    private Business business;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "commentedby")
    private Users users;

    @Column(name = "feedback")
    private String feedback;

    @Column(name = "rating")
    private int rating;

    @Column(name = "createdtime", columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime localDateTime;

    public Comments() {
    }

    public Comments(Business business, Users users, String feedback, int rating) {
        this.business = business;
        this.users = users;
        this.feedback = feedback;
        this.rating = rating;
        this.localDateTime = LocalDateTime.now();
    }

    public long getCommentid() {
        return commentid;
    }

    public void setCommentid(long commentid) {
        this.commentid = commentid;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "commentid=" + commentid +
                ", appointment=" + business +
                ", users=" + users +
                ", feedback='" + feedback + '\'' +
                ", rating=" + rating +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
