package com.team7.appointmentsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "workinghours")
public class BusinessWorkingHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nameofday")
    private String nameOfDay;

    @Column(name = "starthour")
    private Time startHour;

    @Column(name = "endhour")
    private Time endHour;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "businessid")
    private Business business;

    public BusinessWorkingHours() {
    }

    public BusinessWorkingHours(String nameOfDay, Time startHour, Time endHour) {
        this.nameOfDay = nameOfDay;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public BusinessWorkingHours(String nameOfDay, Time startHour, Time endHour, Business business) {
        this.nameOfDay = nameOfDay;
        this.startHour = startHour;
        this.endHour = endHour;
        this.business = business;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameOfDay() {
        return nameOfDay;
    }

    public void setNameOfDay(String nameOfDay) {
        this.nameOfDay = nameOfDay;
    }

    public Time getStartHour() {
        return startHour;
    }

    public void setStartHour(Time startHour) {
        this.startHour = startHour;
    }

    public Time getEndHour() {
        return endHour;
    }

    public void setEndHour(Time endHour) {
        this.endHour = endHour;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return "BusinessWorkingHours{" +
                "id=" + id +
                ", nameOfDay='" + nameOfDay + '\'' +
                ", startHour=" + startHour +
                ", endHour=" + endHour +
                ", business" + business +
                '}';
    }
}
