package com.jaddy.calendarresourceoauth.service;

import com.jaddy.calendarresourceoauth.dao.ManagerDao;
import com.jaddy.calendarresourceoauth.dao.SchduleDao;
import com.jaddy.calendarresourceoauth.dao.SchedulePlanDao;
import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.ds.SchedulePlan;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import com.jaddy.calendarresourceoauth.model.DayPlan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
    private final SchduleDao schduleDao;

    public SchedulePlanService(SchedulePlanDao schedulePlanDao, SchduleDao schduleDao) {
        this.schedulePlanDao = schedulePlanDao;
        this.schduleDao = schduleDao;
    }

    @Transactional
    public SchedulePlan updateSchedulePlanWorkDayMonday(Long scheduleid, DayPlan dayPlan, String userName){


        SchedulePlan schedulePlan = new SchedulePlan();
        DayPlan schedulePlanModel = new DayPlan();
        Manager dbManager = managerDao.findByUsername(userName);

        LOG.info(dbManager.toString());
        if(dbManager == null){
            throw new RuntimeException(" User Cannot be loaded");
        } else {
            Optional<SchedulePlan> schedulePlanDB = schedulePlanDao.findById(scheduleid);
            schedulePlanDB.ifPresent(plan -> schedulePlan.setId(plan.getId()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setSchedule(plan.getSchedule()));
//            schedulePlanModel.setStartOfSchedule(dayPlan.getStartOfSchedule());
            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());
//            schedulePlan.setScheduleStartOfWeek(schedulePlanModel.getStartOfSchedule());
//            schedulePlan.setManager(dbManager);
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
//            schedulePlanModel.setStartOfSchedule(dayPlan.getStartOfSchedule());
            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());
//            schedulePlan.setScheduleStartOfWeek(schedulePlanModel.getStartOfSchedule());
//            schedulePlan.setManager(dbManager);
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
//            schedulePlanModel.setStartOfSchedule(dayPlan.getStartOfSchedule());
            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());
//            schedulePlan.setScheduleStartOfWeek(schedulePlanModel.getStartOfSchedule());
//            schedulePlan.setManager(dbManager);
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




//    public List<SchedulePlan> findSchedulesPlanByManagerId(Long managerId){
//        List<SchedulePlan> schedulePlan2 = schedulePlanDao.findByManagerId(managerId);
//        if(schedulePlan2 == null){
//            throw new RuntimeException("Cannot Find Appointment");
//        } else {
//            return schedulePlan2.stream().collect(Collectors.toList());
//        }
//    }


    @Transactional
    public SchedulePlan createSchedulePlan(Long scheduleId) throws NoSuchAlgorithmException {

        Schedule scheduleExDb = new Schedule();
        SchedulePlan schedulePlanDb = new SchedulePlan();
        Optional<Schedule> scheduleDetailsDB = schduleDao.findById(scheduleId);
        schedulePlanDb.setId(generateRandomId());
        scheduleDetailsDB.ifPresent(plan -> schedulePlanDb.setSchedule(plan));


//        schedulePlanDb.setSchedule(scheduleExDb);

//        SchedulePlan schedulePlan = new SchedulePlan();
//            schedulePlan.setSchedule(scheduleDb);
            schedulePlanDao.save(schedulePlanDb);
//        managerDao.save()
        return schedulePlanDb;
    }






    public List<SchedulePlan> findAllSchedulePlans(){
        List<SchedulePlan> schedulePlan1 = schedulePlanDao.findAll();
        if(schedulePlan1 == null){
            throw new RuntimeException("Cannot Find Appointment");
        } else {
            return schedulePlan1;
        }
    }


    public Long generateRandomId() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstanceStrong();
        Long userId = random.nextLong();
        if(userId.longValue() < 0) return userId * -1;
        return userId;
    }



}
