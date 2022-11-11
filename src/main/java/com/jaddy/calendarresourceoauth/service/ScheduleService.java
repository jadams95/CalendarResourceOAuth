package com.jaddy.calendarresourceoauth.service;


import com.jaddy.calendarresourceoauth.dao.ManagerDao;
import com.jaddy.calendarresourceoauth.dao.SchduleDao;
import com.jaddy.calendarresourceoauth.dao.SchedulePlanDao;
import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.ds.SchedulePlan;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final SchduleDao schduleDao;

    Logger logger = LogManager.getLogger(ScheduleService.class);
    private final ManagerDao managerDao;

    private final SchedulePlanDao schedulePlanDao;

    public ScheduleService(SchduleDao schduleDao, ManagerDao managerDao, SchedulePlanDao schedulePlanDao){
        this.schduleDao = schduleDao;
        this.managerDao = managerDao;
        this.schedulePlanDao = schedulePlanDao;
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

    public Schedule saveJustSchedule(Schedule scheduleEntity){
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

    @Transactional
    public Schedule saveSchedule(Schedule scheduleEntity, Long id, String managerName) throws ParseException {
        Schedule scheduleDb = new Schedule();
        Manager dbManager = managerDao.findByUsername(managerName);
        if(scheduleEntity == null){
            throw new RuntimeException(" User Cannot be loaded");
        } else {

//            schedulePlanDao

            SchedulePlan schedulePlan = new SchedulePlan();
            schedulePlan.setId(id);
            schedulePlanDao.save(schedulePlan);




            scheduleDb.setId(generateRandomId());
            scheduleDb.setName(scheduleEntity.getName());


//            Date testdb = new Date(text.format(dateStringDb));
            logger.info(scheduleEntity.getScheduleStartOfWeek());
//            scheduleDb.setScheduleStartOfWeek(scheduleEntity.getScheduleStartOfWeek());
//            scheduleDb.setDuration(scheduleEntity.getDuration());
            scheduleDb.setScheduleDescription(scheduleEntity.getScheduleDescription());


            // Removed the Many to Many for Schedules and Manager
            // In favor of one to one references of Schedule and Schedule Plan
            scheduleDb.setEditable(scheduleEntity.getEditable());
            scheduleDb.setTargetCustomer(scheduleEntity.getTargetCustomer());
            scheduleDb.setSchedulePlanner(schedulePlan);
            scheduleDb.setManagerSchedule(dbManager);

//            schedulePlan.setMonday(schedulePlanModel);
            schduleDao.save(scheduleDb);
            return scheduleDb;
        }
    }
    public Long generateRandomId(){
        Random random = new Random();
        return random.nextLong();
    }

}
