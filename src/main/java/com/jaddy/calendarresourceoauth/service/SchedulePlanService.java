package com.jaddy.calendarresourceoauth.service;

import com.jaddy.calendarresourceoauth.dao.ManagerDao;
import com.jaddy.calendarresourceoauth.dao.SchedulePlanDao;
import com.jaddy.calendarresourceoauth.ds.SchedulePlan;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import com.jaddy.calendarresourceoauth.model.DayPlan;
import com.jaddy.calendarresourceoauth.model.TimePeriod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;


@Service
public class SchedulePlanService {


    private static final Logger LOG = LoggerFactory.getLogger(SchedulePlanService.class);
    @Autowired
    private ManagerDao managerDao;


    private final SchedulePlanDao schedulePlanDao;

    public SchedulePlanService(SchedulePlanDao schedulePlanDao) {
        this.schedulePlanDao = schedulePlanDao;
    }

    @Transactional
    public SchedulePlan saveSchedulePlanWorkDayMonday(DayPlan dayPlan, String userName){
        SchedulePlan schedulePlan = new SchedulePlan();
        DayPlan schedulePlanModel = new DayPlan();
        Manager dbManager = managerDao.findByUsername(userName);
//        TimePeriod timePeriod = new TimePeriod();
//        schedulePlanModel.setTimePeriod(schedulePlanModel.getTimePeriod());

        LOG.info(dbManager.toString());
        if(dbManager == null){
            throw new RuntimeException(" User Cannot be loaded");
        } else {
            schedulePlan.setId(schedulePlan.getId());
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            schedulePlan.

            schedulePlanModel.setStartOfSchedule(dayPlan.getStartOfSchedule());
            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());
            schedulePlan.setScheduleStartOfWeek(schedulePlanModel.getStartOfSchedule());
            schedulePlan.setManager(dbManager);
            dayPlan.setWorkingHours(schedulePlanModel.getWorkingHours());
            schedulePlan.setMonday(schedulePlanModel);
            schedulePlanDao.save(schedulePlan);
            return schedulePlan;
        }
//        schedulePlan.setManager(scheduleManager);
    }

}
