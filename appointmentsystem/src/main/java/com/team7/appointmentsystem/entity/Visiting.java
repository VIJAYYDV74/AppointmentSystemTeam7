package com.team7.appointmentsystem.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visitings")
public class Visiting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "userid")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "businessid", nullable = false)
    private Business business;

    @Column(name = "searcheddatetime", columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime localDateTime;

    public Visiting() {
    }

    public Visiting(Business business) {
        this.business = business;
    }

    public Visiting(Users users, Business business) {
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Visiting{" +
                "id=" + id +
                ", users=" + users +
                ", business=" + business +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
