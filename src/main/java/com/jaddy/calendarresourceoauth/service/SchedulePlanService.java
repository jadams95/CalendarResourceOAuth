package com.jaddy.calendarresourceoauth.service;

import com.jaddy.calendarresourceoauth.dao.ManagerDao;
import com.jaddy.calendarresourceoauth.dao.SchduleDao;
import com.jaddy.calendarresourceoauth.dao.SchedulePlanDao;
import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.ds.SchedulePlan;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import com.jaddy.calendarresourceoauth.model.DayPlan;
import com.jaddy.calendarresourceoauth.model.dtos.ScheduleDTO;
import com.jaddy.calendarresourceoauth.model.dtos.SchedulePlanDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
    public List<SchedulePlanDTO> findAllSchedulePlans(){
        return schedulePlanDao.findAll().stream().map(
                schedulePlan -> {
                    Optional<SchedulePlan> schedulePlanDbEntity = schedulePlanDao.findById(schedulePlan.getId());
                    SchedulePlanDTO schedulePlan2 = new SchedulePlanDTO();

                    schedulePlanDbEntity.ifPresent(x -> schedulePlan2.setSchedulePlanId(x.getId()));


                    // get the Schedule to link to SchedulePLanner
                    Optional<Schedule> scheduleEntity = schduleDao.findById(schedulePlan2.getSchedulePlanId());
                    Schedule scheduleEx = new Schedule();

                    // get the Manager for SchedulePlanner




                    scheduleEntity.ifPresent(x -> scheduleEx.setId(x.getId()));
                    scheduleEntity.ifPresent(x -> scheduleEx.setName(x.getName()));
                    scheduleEntity.ifPresent(x -> scheduleEx.setScheduleDescription(x.getScheduleDescription()));
                    scheduleEntity.ifPresent(x -> scheduleEx.setTargetCustomer(x.getTargetCustomer()));
                    scheduleEntity.ifPresent(x -> scheduleEx.setEditable(x.getEditable()));
                    scheduleEntity.ifPresent(x -> scheduleEx.managerSchedule = x.getManagerSchedule());
                    schedulePlanDbEntity.ifPresent(x -> schedulePlan2.setScheduleDetails(scheduleEx));


                    // Setting up the SchedulePlan DayPlanner
                    schedulePlanDbEntity.ifPresent(x -> schedulePlan2.setMonday(x.getMonday()));
                    schedulePlanDbEntity.ifPresent(x -> schedulePlan2.setTuesday(x.getTuesday()));
                    schedulePlanDbEntity.ifPresent(x -> schedulePlan2.setWednesday(x.getWednesday()));
                    schedulePlanDbEntity.ifPresent(x -> schedulePlan2.setThursday(x.getThursday()));
                    schedulePlanDbEntity.ifPresent(x -> schedulePlan2.setFriday(x.getFriday()));
                    schedulePlanDbEntity.ifPresent(x -> schedulePlan2.setSaturday(x.getSaturday()));
                    schedulePlanDbEntity.ifPresent(x -> schedulePlan2.setSunday(x.getSunday()));

                    return schedulePlan2;
                }).toList();
    }

    /**
     * A Service method to find all SchedulesPlans from teh Database
     * @return
     */
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
    public SchedulePlan updateSchedulePlanWorkDayMonday(Long scheduleid, Long schedulePlanId, DayPlan dayPlan, String userName){

        Optional<Schedule> optionalSchedule = schduleDao.findById(scheduleid);

        SchedulePlan schedulePlan = new SchedulePlan();
        DayPlan schedulePlanModel = new DayPlan();
        Manager dbManager = managerDao.findByUsername(userName);

        LOG.info(dbManager.toString());
        if(dbManager == null){
            throw new RuntimeException(" User Cannot be loaded");
        } else {

            schedulePlanModel.setEventId(generateRandomUUID());
            schedulePlanModel.setEventName(dayPlan.getEventName());
            schedulePlanModel.setEventPlannerDate(dayPlan.getEventPlannerDate());
            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());
            Optional<SchedulePlan> schedulePlanDB = schedulePlanDao.findById(schedulePlanId);
            schedulePlanDB.ifPresent(plan -> schedulePlan.setId(plan.getId()));
            schedulePlanDB.ifPresent(plan -> plan.setSchedule(optionalSchedule.orElseThrow()));
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
            schedulePlanModel.setEventId(generateRandomUUID());
            schedulePlanModel.setEventName(dayPlan.getEventName());
            schedulePlanModel.setEventPlannerDate(dayPlan.getEventPlannerDate());
            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());

            schedulePlanDB.ifPresent(plan -> schedulePlan.setId(plan.getId()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setSchedule(plan.getSchedule()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setMonday(plan.getMonday()));



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
            schedulePlanModel.setEventId(generateRandomUUID());
            schedulePlanModel.setEventName(dayPlan.getEventName());
            schedulePlanModel.setEventPlannerDate(dayPlan.getEventPlannerDate());
            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());

            schedulePlanDB.ifPresent(plan -> schedulePlan.setId(plan.getId()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setSchedule(plan.getSchedule()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setMonday(plan.getMonday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setTuesday(plan.getTuesday()));

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
            schedulePlanModel.setEventId(generateRandomUUID());
            schedulePlanModel.setEventName(dayPlan.getEventName());
            schedulePlanModel.setEventPlannerDate(dayPlan.getEventPlannerDate());
            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());

            schedulePlanDB.ifPresent(plan -> schedulePlan.setId(plan.getId()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setSchedule(plan.getSchedule()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setMonday(plan.getMonday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setTuesday(plan.getTuesday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setWednesday(plan.getWednesday()));

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
            schedulePlanModel.setEventId(generateRandomUUID());
            schedulePlanModel.setEventName(dayPlan.getEventName());
            schedulePlanModel.setEventPlannerDate(dayPlan.getEventPlannerDate());
            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());

            schedulePlanDB.ifPresent(plan -> schedulePlan.setId(plan.getId()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setSchedule(scheduleEx.get()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setMonday(plan.getMonday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setTuesday(plan.getTuesday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setWednesday(plan.getWednesday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setThursday(plan.getThursday()));
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

            schedulePlanModel.setEventId(generateRandomUUID());
            schedulePlanModel.setEventName(dayPlan.getEventName());
            schedulePlanModel.setEventPlannerDate(dayPlan.getEventPlannerDate());
            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());

            schedulePlanDB.ifPresent(plan -> schedulePlan.setId(plan.getId()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setSchedule(plan.getSchedule()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setMonday(plan.getMonday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setTuesday(plan.getTuesday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setWednesday(plan.getWednesday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setThursday(plan.getThursday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setFriday(plan.getFriday()));

//            dayPlan.setWorkingHours(schedulePlanModel.getWorkingHours());
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
            schedulePlanModel.setEventId(generateRandomUUID());
            schedulePlanModel.setEventName(dayPlan.getEventName());
            schedulePlanModel.setEventPlannerDate(dayPlan.getEventPlannerDate());
            schedulePlanModel.setWorkingHours(dayPlan.getWorkingHours());

            // this line doesn't make sense to me
//            dayPlan.setWorkingHours(schedulePlanModel.getWorkingHours());

            schedulePlanDB.ifPresent(plan -> schedulePlan.setId(plan.getId()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setSchedule(plan.getSchedule()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setMonday(plan.getMonday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setTuesday(plan.getTuesday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setWednesday(plan.getWednesday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setThursday(plan.getThursday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setFriday(plan.getFriday()));
            schedulePlanDB.ifPresent(plan -> schedulePlan.setSaturday(plan.getSaturday()));


            schedulePlan.setSunday(schedulePlanModel);
            schedulePlanDao.save(schedulePlan);
            return schedulePlan;
        }
    }

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
        schedulePlanDao.save(schedulePlanDb);
        return schedulePlanDb;
    }








    public Long generateRandomId() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstanceStrong();
        Long userId = random.nextLong();
        if(userId.longValue() < 0) return userId * -1;
        return userId;
    }

    public UUID generateRandomUUID() {
        return UUID.randomUUID();
    }

}
