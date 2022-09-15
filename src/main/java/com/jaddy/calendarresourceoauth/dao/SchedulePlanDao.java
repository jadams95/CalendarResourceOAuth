package com.jaddy.calendarresourceoauth.dao;

import com.jaddy.calendarresourceoauth.ds.SchedulePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulePlanDao extends JpaRepository<SchedulePlan, Long> {

}
