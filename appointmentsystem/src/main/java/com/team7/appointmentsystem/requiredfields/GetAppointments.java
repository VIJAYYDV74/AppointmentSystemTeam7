package com.team7.appointmentsystem.requiredfields;

import java.sql.Time;
import java.util.Date;

public interface GetAppointments {
    Date getAppointmentDate();
    Time getBeginTime();
    Time getEndTime();
    int getTotalPrice();
    String getStatus();
    String getUsers();
}
