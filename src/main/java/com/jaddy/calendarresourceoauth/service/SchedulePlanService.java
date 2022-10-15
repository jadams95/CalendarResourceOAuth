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
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;


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

        LOG.info(dbManager.toString());
        if(dbManager == null){
            throw new RuntimeException(" User Cannot be loaded");
        } else {
            schedulePlan.setId(schedulePlan.getId());
            schedulePlanModel.setStartOfSchedule(dayPlan.getStartOfSchedule());
            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());
            schedulePlan.setScheduleStartOfWeek(schedulePlanModel.getStartOfSchedule());
            schedulePlan.setManager(dbManager);
            dayPlan.setWorkingHours(schedulePlanModel.getWorkingHours());
            schedulePlan.setMonday(schedulePlanModel);
            schedulePlanDao.save(schedulePlan);
            return schedulePlan;
        }
    }


    public SchedulePlan saveSchedulePlanWorkDayTuesday(DayPlan dayPlan, String userName){
        SchedulePlan schedulePlan = new SchedulePlan();
        DayPlan schedulePlanModel = new DayPlan();
        Manager dbManager = managerDao.findByUsername(userName);

        LOG.info(dbManager.toString());
        if(dbManager == null){
            throw new RuntimeException(" User Cannot be loaded");
        } else {
            schedulePlan.setId(schedulePlan.getId());
            schedulePlanModel.setStartOfSchedule(dayPlan.getStartOfSchedule());
            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());
            schedulePlan.setScheduleStartOfWeek(schedulePlanModel.getStartOfSchedule());
            schedulePlan.setManager(dbManager);
            dayPlan.setWorkingHours(schedulePlanModel.getWorkingHours());
            schedulePlan.setTuesday(schedulePlanModel);
            schedulePlanDao.save(schedulePlan);
            return schedulePlan;
        }
    }


    @Transactional
    public SchedulePlan saveSchedulePlanWorkDayWednesday(DayPlan dayPlan, String userName, Long id){
        SchedulePlan schedulePlan = new SchedulePlan();
        DayPlan schedulePlanModel = new DayPlan();
        Manager dbManager = managerDao.findByUsername(userName);





        LOG.info(dbManager.toString());
        if(dbManager == null){
            throw new RuntimeException(" User Cannot be loaded");
        } else {

//            schedulePlanDao.findAllByScheduleStartOfWeek()
//            schedulePlan.setId(generateRandomId());
            schedulePlanModel.setStartOfSchedule(dayPlan.getStartOfSchedule());
            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());
            schedulePlan.setScheduleStartOfWeek(schedulePlanModel.getStartOfSchedule());
            schedulePlan.setManager(dbManager);
            dayPlan.setWorkingHours(schedulePlanModel.getWorkingHours());
//            schedulePlan.setMonday();
            schedulePlan.setWednesday(schedulePlanModel);
            schedulePlanDao.save(schedulePlan);
            return schedulePlan;
        }
    }
    public SchedulePlan findSchedulePlanById(Long id){
      Optional<SchedulePlan> schedulePlan1 = schedulePlanDao.findById(id);
      if(schedulePlan1 == null){
          throw new RuntimeException("Cannot Find Appointment");
      } else {
          return schedulePlan1.get();
      }
    }




    public List<SchedulePlan> findSchedulesPlanByManagerId(Long managerId){
        List<SchedulePlan> schedulePlan2 = schedulePlanDao.findByManagerId(managerId);
        if(schedulePlan2 == null){
            throw new RuntimeException("Cannot Find Appointment");
        } else {
            return schedulePlan2.stream().collect(Collectors.toList());
        }
    }






    public List<SchedulePlan> findAllSchedulePlans(){
        List<SchedulePlan> schedulePlan1 = schedulePlanDao.findAll();
        if(schedulePlan1 == null){
            throw new RuntimeException("Cannot Find Appointment");
        } else {
            return schedulePlan1;
        }
    }


        public Long generateRandomId(){
        Random random = new Random();
        return random.nextLong();
    }



}
