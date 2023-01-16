package com.jaddy.calendarresourceoauth.dao;

import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface SchduleDao extends JpaRepository<Schedule, Long> {
// Commented out need to test
//    @Query("SELECT s from Schedule s where s.managerSchedule = :managerSchedule")
//    Optional<List<Schedule>> findSchedulesByManagerSchedule(String username);

}
