package com.jaddy.calendarresourceoauth.dao;

import com.jaddy.calendarresourceoauth.ds.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SchduleDao extends JpaRepository<Schedule, Long> {
}
