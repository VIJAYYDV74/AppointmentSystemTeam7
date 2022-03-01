package com.team7.appointmentsystem.resultapis;

import java.sql.Time;

public class AppointmentSlots {
    Time beginTime;
    Time endTime;
    boolean isAvailable;

    public AppointmentSlots(Time beginTime, Time endTime, boolean isAvailable) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.isAvailable = isAvailable;
    }

    public AppointmentSlots(Time beginTime, Time endTime) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.isAvailable = true;
    }

    public Time getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Time beginTime) {
        this.beginTime = beginTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
