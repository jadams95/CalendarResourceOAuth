package com.jaddy.calendarresourceoauth.dao;

import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SchduleDao extends JpaRepository<Schedule, Long> {
    Manager findSchedulesByManagerSchedule_Id(Long managerId);
}
