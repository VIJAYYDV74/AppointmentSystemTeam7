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
    private Business businessHours;

    public BusinessWorkingHours() {
    }

    public BusinessWorkingHours(String nameOfDay, Time startHour, Time endHour) {
        this.nameOfDay = nameOfDay;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public BusinessWorkingHours(String nameOfDay, Time startHour, Time endHour, Business businessHours) {
        this.nameOfDay = nameOfDay;
        this.startHour = startHour;
        this.endHour = endHour;
        this.businessHours = businessHours;
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

    public Business getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(Business businessHours) {
        this.businessHours = businessHours;
    }

    @Override
    public String toString() {
        return "BusinessWorkingHours{" +
                "id=" + id +
                ", nameOfDay='" + nameOfDay + '\'' +
                ", startHour=" + startHour +
                ", endHour=" + endHour +
                ", businessHour" + businessHours +
                '}';
    }
}
