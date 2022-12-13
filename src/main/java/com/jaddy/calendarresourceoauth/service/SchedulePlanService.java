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
    public SchedulePlan updateSchedulePlanWorkDayMonday(Long scheduleid, Long schedulePlanId, DayPlan dayPlan, String userName){

        Optional<Schedule> optionalSchedule = schduleDao.findById(scheduleid);

        SchedulePlan schedulePlan = new SchedulePlan();
        DayPlan schedulePlanModel = new DayPlan();
        Manager dbManager = managerDao.findByUsername(userName);

        LOG.info(dbManager.toString());
        if(dbManager == null){
            throw new RuntimeException(" User Cannot be loaded");
        } else {

            Optional<SchedulePlan> schedulePlanDB = schedulePlanDao.findById(schedulePlanId);
            schedulePlanDB.ifPresent(plan -> schedulePlan.setId(plan.getId()));
//            Schedule testSchedule = new Schedule();
//
//            schduleDao.save(testSchedule);
            schedulePlanDB.ifPresent(plan -> plan.setSchedule(optionalSchedule.orElseThrow()));
            schedulePlanModel.setEventPlannerDate(dayPlan.getEventPlannerDate());
            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());
            dayPlan.setWorkingHours(schedulePlanModel.getWorkingHours());
            schedulePlan.setMonday(schedulePlanModel);
            return schedulePlanDao.save(schedulePlan);
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

            schedulePlanModel.setEventPlannerDate(dayPlan.getEventPlannerDate());
            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());
            dayPlan.setWorkingHours(schedulePlanModel.getWorkingHours());
            schedulePlan.setTuesday(schedulePlanModel);
            schedulePlanDao.save(schedulePlan);
            return schedulePlan;
        }
    }


    @Transactional
    public List<SchedulePlan> findAllSchedules(){
        return schedulePlanDao.findAll().stream().map(schedule -> {
            Optional<SchedulePlan> scheduleAllRespDB = schedulePlanDao.findById(schedule.getId());
//            Manager managerDb = managerDao.findByUsername(managerName);
            SchedulePlan scheduleDTO = new SchedulePlan();
            scheduleAllRespDB.ifPresent(x -> scheduleDTO.setId(x.getId()));
            scheduleAllRespDB.ifPresent(x -> scheduleDTO.setMonday(x.getMonday()));
            scheduleAllRespDB.ifPresent(x -> scheduleDTO.setTuesday(x.getTuesday()));
            scheduleAllRespDB.ifPresent(x -> scheduleDTO.setWednesday(x.getWednesday()));
            scheduleAllRespDB.ifPresent(x -> scheduleDTO.setThursday(x.getThursday()));
            scheduleAllRespDB.ifPresent(x -> scheduleDTO.setFriday(x.getFriday()));
            scheduleAllRespDB.ifPresent(x -> scheduleDTO.setSaturday(x.getSaturday()));
            scheduleAllRespDB.ifPresent(x -> scheduleDTO.setSunday(x.getSunday()));
//            scheduleAllRespDB.ifPresent(x -> scheduleDTO.setSchedule(x.getSchedule()));
            return scheduleDTO;
        }).toList();
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
            schedulePlanModel.setEventPlannerDate(dayPlan.getEventPlannerDate());
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
            schedulePlanModel.setEventPlannerDate(dayPlan.getEventPlannerDate());
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
        Optional<Schedule> scheduleEx = schduleDao.findById(scheduleid);

        LOG.info(dbManager.toString());
        if(dbManager == null){
            throw new RuntimeException(" User Cannot be loaded");
        } else {
            Optional<SchedulePlan> schedulePlanDB = schedulePlanDao.findById(scheduleid);
            schedulePlanDB.ifPresent(plan -> schedulePlan.setId(plan.getId()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setSchedule(scheduleEx.get()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setMonday(plan.getMonday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setTuesday(plan.getTuesday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setWednesday(plan.getWednesday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setThursday(plan.getThursday()));
//            schedulePlanDB.ifPresent(plan -.);

            schedulePlanModel.setEventPlannerDate(dayPlan.getEventPlannerDate());
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
            schedulePlanModel.setEventPlannerDate(dayPlan.getEventPlannerDate());
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
            schedulePlanModel.setEventPlannerDate(dayPlan.getEventPlannerDate());
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
        schedulePlanDb.setSchedule(scheduleDetailsDB.orElseThrow());
//        schedulePlanDb.setId(generateRandomId());

//        scheduleDetailsDB.ifPresent(schedule -> scheduleExDb.setId(scheduleId));
//        scheduleDetailsDB.ifPresent(schedule -> scheduleExDb.setName(schedule.getName()));
//        scheduleDetailsDB.ifPresent(schedule -> scheduleExDb.setScheduleDescription(schedule.getScheduleDescription()));
//        scheduleDetailsDB.ifPresent(schedule -> scheduleExDb.setTargetCustomer(schedule.getTargetCustomer()));
//        scheduleDetailsDB.ifPresent(schedule -> scheduleExDb.setEditable(schedule.getEditable()));


            schedulePlanDao.save(schedulePlanDb);


        //        scheduleDetailsDB.ifPresent(plan -> schedulePlanDb.setSchedule(plan));


//        schedulePlanDb.setSchedule(scheduleExDb);

//        SchedulePlan schedulePlan = new SchedulePlan();
//            schedulePlan.setSchedule(scheduleDb);
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
