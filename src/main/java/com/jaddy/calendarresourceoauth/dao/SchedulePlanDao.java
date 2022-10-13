package com.jaddy.calendarresourceoauth.dao;

import com.jaddy.calendarresourceoauth.ds.SchedulePlan;
import com.jaddy.calendarresourceoauth.model.DayPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SchedulePlanDao extends JpaRepository<SchedulePlan, Long> {
        List<SchedulePlan> findByManagerId(Long managerId);

        @Query("select sp from SchedulePlan sp where sp.scheduleStartOfWeek = :startOfWeek")
        List<DayPlan> findAllByScheduleStartOfWeek(@Param("startOfWeek") String startOfWeek);
        @Override
        @Query("select sp from SchedulePlan sp")
        List<SchedulePlan> findAll();
}
