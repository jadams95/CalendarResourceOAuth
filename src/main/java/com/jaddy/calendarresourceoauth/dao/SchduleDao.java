package com.jaddy.calendarresourceoauth.dao;

import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SchduleDao extends JpaRepository<Schedule, Long> {


//     @Query("select Schedule where from S")
//     List<Schedule> findByManagerSchedule_Id(Long managerId);


}
