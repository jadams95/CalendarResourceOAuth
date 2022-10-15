package com.jaddy.calendarresourceoauth.dao;

import com.jaddy.calendarresourceoauth.ds.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentDao extends JpaRepository<Appointment, Long> {
}
