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
            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());
            dayPlan.setWorkingHours(schedulePlanModel.getWorkingHours());
            schedulePlan.setMonday(schedulePlanModel);
            schedulePlanDao.save(schedulePlan);
            return schedulePlan;
        }
    }


    @Transactional
    public SchedulePlan updateSchedulePlanWorkDayTuesday(Long scheduleid, DayPlan dayPlan, String userName){
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
            schedulePlanDB.ifPresent(plan -> schedulePlan.setMonday(plan.getMonday()));

            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());
            dayPlan.setWorkingHours(schedulePlanModel.getWorkingHours());
            schedulePlan.setTuesday(schedulePlanModel);
            schedulePlanDao.save(schedulePlan);
            return schedulePlan;
        }
    }

    @Transactional
    public SchedulePlan updateSchedulePlanWorkDayWednesday(Long scheduleid, DayPlan dayPlan, String userName){
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
            schedulePlanDB.ifPresent(plan -> schedulePlan.setMonday(plan.getMonday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setTuesday(plan.getTuesday()));

            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());
            dayPlan.setWorkingHours(schedulePlanModel.getWorkingHours());
            schedulePlan.setWednesday(schedulePlanModel);
            schedulePlanDao.save(schedulePlan);
            return schedulePlan;
        }
    }

    @Transactional
    public SchedulePlan updateSchedulePlanWorkDayThursday(Long scheduleid, DayPlan dayPlan, String userName){
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
            schedulePlanDB.ifPresent(plan -> schedulePlan.setMonday(plan.getMonday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setTuesday(plan.getTuesday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setWednesday(plan.getWednesday()));

            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());
            dayPlan.setWorkingHours(schedulePlanModel.getWorkingHours());
            schedulePlan.setThursday(schedulePlanModel);
            schedulePlanDao.save(schedulePlan);
            return schedulePlan;
        }
    }

    @Transactional
    public SchedulePlan updateSchedulePlanWorkDayFriday(Long scheduleid, DayPlan dayPlan, String userName){
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
            schedulePlanDB.ifPresent(plan -> schedulePlan.setMonday(plan.getMonday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setTuesday(plan.getTuesday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setWednesday(plan.getWednesday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setThursday(plan.getThursday()));


            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());
            dayPlan.setWorkingHours(schedulePlanModel.getWorkingHours());
            schedulePlan.setFriday(schedulePlanModel);
            schedulePlanDao.save(schedulePlan);
            return schedulePlan;
        }
    }

    @Transactional
    public SchedulePlan updateSchedulePlanWorkDaySaturday(Long scheduleid, DayPlan dayPlan, String userName){
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
            schedulePlanDB.ifPresent(plan -> schedulePlan.setMonday(plan.getMonday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setTuesday(plan.getTuesday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setWednesday(plan.getWednesday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setThursday(plan.getThursday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setFriday(plan.getFriday()));

            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());
            dayPlan.setWorkingHours(schedulePlanModel.getWorkingHours());
            schedulePlan.setSaturday(schedulePlanModel);
            schedulePlanDao.save(schedulePlan);
            return schedulePlan;
        }
    }

    @Transactional
    public SchedulePlan updateSchedulePlanWorkDaySunday(Long scheduleid, DayPlan dayPlan, String userName){
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
            schedulePlanDB.ifPresent(plan -> schedulePlan.setMonday(plan.getMonday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setTuesday(plan.getTuesday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setWednesday(plan.getWednesday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setThursday(plan.getThursday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setFriday(plan.getFriday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setSaturday(plan.getSaturday()));
            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());
            dayPlan.setWorkingHours(schedulePlanModel.getWorkingHours());

            schedulePlan.setSunday(schedulePlanModel);
            schedulePlanDao.save(schedulePlan);
            return schedulePlan;
        }
    }

// The idea was to find the manager and select all the schedules where manager.id = schedule_plans.id_manager
//    public List<SchedulePlan> findSchedulesPlanByManagerId(Long managerId){
//        List<SchedulePlan> schedulePlan2 = schedulePlanDao.findByManagerId(managerId);
//        if(schedulePlan2 == null){
//            throw new RuntimeException("Cannot Find Appointment");
//        } else {
//            return schedulePlan2.stream().collect(Collectors.toList());
//        }
//    }


    /**
     * Finds a Schedule by ScheduleId and creates a SchedulePlan in the database with the SchedulePlan linked to Schedule
     * @param scheduleId
     * @return
     * @throws NoSuchAlgorithmException
     */
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
