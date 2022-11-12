package com.jaddy.calendarresourceoauth.dao;

import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.ds.SchedulePlan;
import com.jaddy.calendarresourceoauth.model.DayPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchedulePlanDao extends JpaRepository<SchedulePlan, Long> {
//        List<SchedulePlan> findByManagerId(Long managerId);
//
//        @Query("select sp from SchedulePlan sp where sp.scheduleStartOfWeek = :startOfWeek")
//        List<DayPlan> findAllByScheduleStartOfWeek(@Param("startOfWeek") String startOfWeek);
//        @Override
//        @Query("select sp from SchedulePlan sp")
//        List<SchedulePlan> findAll();

//        @Query("select sp from SchedulePlan sp where sp.manager =: id_manager")
//        Optional<SchedulePlan> findSchedulesByManager(Long managerId);
}
