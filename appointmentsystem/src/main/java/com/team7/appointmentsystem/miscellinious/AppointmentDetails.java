package com.team7.appointmentsystem.miscellinious;

import com.team7.appointmentsystem.entity.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

public interface AppointmentDetails {
    long getAppointmentid();
    LocalDateTime getBookedDate();
    Date getAppointmentDate();
    Time getBeginTime();
    Time getEndTime();
    int getTotalPrice();
    String getStatus();
    String getCancellationReason();
    Services getServices();
    Payments getPayments();
}
