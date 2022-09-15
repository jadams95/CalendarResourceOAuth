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
    public SchedulePlan saveSchedulePlanWorkDayMonday(TimePeriod timePeriod2, String userName){
        SchedulePlan schedulePlan = new SchedulePlan();
        DayPlan dayPlan = new DayPlan();
        Manager dbManager = managerDao.findByUsername(userName);

       LOG.info(dbManager.toString());

        if(dbManager == null){
            throw new RuntimeException(" User Cannot be loaded");
        } else {

            schedulePlan.setManager(dbManager);
//            schedulePlan.setId();
//           schedulePlan.setId(manager.getId());
//            dbManager.ifPresent(manager -> schedulePlan.setId(manager.getId()));
//            schedulePlan.setId(userManager.getId());
//            timePeriod.setStart(timePeriod2.getStart());
//            timePeriod.setEnd(timePeriod2.getEnd());

            dayPlan.setWorkingHours(timePeriod2);
            schedulePlan.setMonday(dayPlan);
            schedulePlanDao.save(schedulePlan);
            return schedulePlan;
        }
//        schedulePlan.setManager(scheduleManager);
    }

}
