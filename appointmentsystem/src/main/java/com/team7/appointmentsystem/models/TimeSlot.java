package com.team7.appointmentsystem.models;

import java.sql.Date;
import java.sql.Time;

public class TimeSlot {
    private Time beginTime;
    private Time endTime;
    private String rescheduleDate;

    public TimeSlot(Time beginTime, Time endTime, String rescheduleDate) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.rescheduleDate = rescheduleDate;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", rescheduleDate='" + rescheduleDate + '\'' +
                '}';
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

    public String getRescheduleDate() {
        return rescheduleDate;
    }

    public void setRescheduleDate(String rescheduleDate) {
        this.rescheduleDate = rescheduleDate;
    }
}
