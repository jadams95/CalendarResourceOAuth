package com.jaddy.calendarresourceoauth.service;


import com.jaddy.calendarresourceoauth.dao.ManagerDao;
import com.jaddy.calendarresourceoauth.dao.SchduleDao;
import com.jaddy.calendarresourceoauth.dao.SchedulePlanDao;
import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.ds.SchedulePlan;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import com.jaddy.calendarresourceoauth.model.dtos.ScheduleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final SchduleDao schduleDao;

    Logger logger = LogManager.getLogger(ScheduleService.class);
    private final ManagerDao managerDao;

    private final SchedulePlanService schedulePlanService;
    private final SchedulePlanDao schedulePlanDao;

    public ScheduleService(SchduleDao schduleDao, ManagerDao managerDao, SchedulePlanDao schedulePlanDao, SchedulePlanService schedulePlanService){
        this.schduleDao = schduleDao;
        this.managerDao = managerDao;
        this.schedulePlanDao = schedulePlanDao;
        this.schedulePlanService = schedulePlanService;
    }



    public List<Schedule> getScheduleByUserName(String userName){
//        Schedule scheduleDb = new Schedule();
        List<Schedule> scheList;
        Manager dbManager = managerDao.findByUsername(userName);
        if(userName == null){
            throw new RuntimeException(" User Cannot be loaded");
        }
        else {
           scheList = dbManager.getSchedules();
           return scheList;
        }


    }

    public Schedule saveJustSchedule(Schedule scheduleEntity) throws NoSuchAlgorithmException {
        Schedule scheduleDb = new Schedule();
//        Manager dbManager = managerDao.findByUsername(userName);
        if(scheduleEntity == null){
            throw new RuntimeException(" User Cannot be loaded");
        } else {

//            schedulePlanDao



            scheduleDb.setId(generateRandomId());
            scheduleDb.setName(scheduleEntity.getName());
//            scheduleDb.setDuration(scheduleEntity.getDuration());
            scheduleDb.setScheduleDescription(scheduleEntity.getScheduleDescription());


            // Removed the Many to Many for Schedules and Manager
            // In favor of one to one references of Schedule and Schedule Plan
            scheduleDb.setEditable(scheduleEntity.getEditable());
            scheduleDb.setTargetCustomer(scheduleEntity.getTargetCustomer());
//            scheduleDb.setSchedulePlanner(schedulePlan);
//            schedulePlan.setMonday(schedulePlanModel);
            schduleDao.save(scheduleDb);
            return scheduleDb;
        }
    }

//    public Schedule findScheduleById(Long scheduleId){
//       Optional<Schedule> scheduleDb = schduleDao.findById(scheduleId);
//        return
//    }

    /**
     *
     */
    @Transactional
    public Schedule saveSchedule(Schedule scheduleEntity, String managerName) throws ParseException, NoSuchAlgorithmException {
        Long scheduleIdent = generateRandomId();
//        ScheduleDTO scheduleDTO = new ScheduleDTO();
        Manager dbManager = managerDao.findByUsername(managerName);
        if(dbManager == null){
            throw new RuntimeException(" Schedule Cannot be found for User");
        } else {
            Schedule scheduleDb = new Schedule();
            SchedulePlan schedulePlan = new SchedulePlan();




            scheduleDb.setId(scheduleIdent);
            scheduleDb.setSchedulePlan(null);
            scheduleDb.setName(scheduleEntity.getName());
            scheduleDb.setScheduleDescription(scheduleEntity.getScheduleDescription());
            scheduleDb.setScheduleStartOfWeek(null);
            scheduleDb.setTargetCustomer(scheduleEntity.getTargetCustomer());
            scheduleDb.setEditable(scheduleEntity.getEditable());
            scheduleDb.setManagerSchedule(null);
//            schedulePlan.setSchedule(scheduleDb);
//            schedulePlanDao.save(schedulePlan);
            schduleDao.save(scheduleDb);
//            Optional<Schedule> scheduleRespDB = schduleDao.findById(testSchedule.getId());
////            scheduleDb.setSchedulePlanner(schedulePlan);
//
////            schedulePlan.setMonday(schedulePlanModel);
//
//            scheduleRespDB.ifPresent(x -> scheduleDTO.setId(x.getId()));
//            scheduleRespDB.ifPresent(x -> scheduleDTO.setName(x.getName()));
//            scheduleRespDB.ifPresent(x -> scheduleDTO.setScheduleDescription(x.getScheduleDescription()));
//            scheduleRespDB.ifPresent(x -> scheduleDTO.setTargetCustomer(x.getTargetCustomer()));
//            scheduleRespDB.ifPresent(x -> scheduleDTO.setEditable(x.getEditable()));

            return scheduleDb;
        }
    }

    @Transactional
    public List<Schedule> findAllSchedules(){
        return schduleDao.findAll();
    }
    public Long generateRandomId() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstanceStrong();
        Long userId = random.nextLong();
        if(userId.longValue() < 0) return userId * -1;
        return userId;
    }

}
