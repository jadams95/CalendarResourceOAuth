package com.jaddy.calendarresourceoauth.dao;

import com.jaddy.calendarresourceoauth.ds.SchedulePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchedulePlanDao extends JpaRepository<SchedulePlan, Long> {
        List<SchedulePlan> findByManagerId(Long managerId);

        @Override
        @Query("select sp from SchedulePlan sp")
        List<SchedulePlan> findAll();
}
