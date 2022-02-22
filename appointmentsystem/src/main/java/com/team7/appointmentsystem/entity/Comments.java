package com.team7.appointmentsystem.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentid;

    @OneToOne
    @JoinColumn(name = "appointmentid")
    private Appointment appointment;

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

    public Comments(Appointment appointment, Users users, String feedback, int rating) {
        this.appointment = appointment;
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

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
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
                ", appointment=" + appointment +
                ", users=" + users +
                ", feedback='" + feedback + '\'' +
                ", rating=" + rating +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
