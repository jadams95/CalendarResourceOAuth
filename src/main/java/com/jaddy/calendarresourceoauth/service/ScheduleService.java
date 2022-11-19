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
import java.util.*;
//
//@Service
//public class ScheduleService {
//
//    private final SchduleDao schduleDao;
//
//    Logger logger = LogManager.getLogger(ScheduleService.class);
//    private final ManagerDao managerDao;
//
//    private final SchedulePlanService schedulePlanService;
//    private final SchedulePlanDao schedulePlanDao;
//
//    public ScheduleService(SchduleDao schduleDao, ManagerDao managerDao, SchedulePlanDao schedulePlanDao, SchedulePlanService schedulePlanService){
//        this.schduleDao = schduleDao;
//        this.managerDao = managerDao;
//        this.schedulePlanDao = schedulePlanDao;
//        this.schedulePlanService = schedulePlanService;
//    }
//
//
//
//
////    public Schedule findScheduleById(Long scheduleId){
////       Optional<Schedule> scheduleDb = schduleDao.findById(scheduleId);
////        return
////    }
//
//    /**
//     *
//     */
//    @Transactional
//    public Schedule saveSchedule(Schedule scheduleEntity, String managerName) throws ParseException, NoSuchAlgorithmException {
//        Long scheduleIdent = generateRandomId();
////        ScheduleDTO scheduleDTO = new ScheduleDTO();
//        Manager dbManager = managerDao.findByUsername(managerName);
//        if(dbManager == null){
//            throw new RuntimeException(" Schedule Cannot be found for User");
//        } else {
//            Schedule scheduleDb = new Schedule();
//            SchedulePlan schedulePlan = new SchedulePlan();
//
//
//
//
//            scheduleDb.setId(scheduleIdent);
////            scheduleDb.setSchedulePlan(null);
//            scheduleDb.setName(scheduleEntity.getName());
//            scheduleDb.setScheduleDescription(scheduleEntity.getScheduleDescription());
////            scheduleDb.setScheduleStartOfWeek(null);
//            scheduleDb.setTargetCustomer(scheduleEntity.getTargetCustomer());
//            scheduleDb.setEditable(scheduleEntity.getEditable());
////            scheduleDb.setManagerSchedule(List.of(dbManager));
////            schedulePlan.setSchedule(scheduleDb);
////            schedulePlanDao.save(schedulePlan);
//            schduleDao.save(scheduleDb);
////            Optional<Schedule> scheduleRespDB = schduleDao.findById(testSchedule.getId());
//////            scheduleDb.setSchedulePlanner(schedulePlan);
////
//////            schedulePlan.setMonday(schedulePlanModel);
////
////            scheduleRespDB.ifPresent(x -> scheduleDTO.setId(x.getId()));
////            scheduleRespDB.ifPresent(x -> scheduleDTO.setName(x.getName()));
////            scheduleRespDB.ifPresent(x -> scheduleDTO.setScheduleDescription(x.getScheduleDescription()));
////            scheduleRespDB.ifPresent(x -> scheduleDTO.setTargetCustomer(x.getTargetCustomer()));
////            scheduleRespDB.ifPresent(x -> scheduleDTO.setEditable(x.getEditable()));
//
//            return scheduleDb;
//        }
//    }
//
//    @Transactional
//    public List<ScheduleDTO> findAllSchedules(){
//        return schduleDao.findAll().stream().map(schedule -> {
//            Optional<Schedule> scheduleAllRespDB = schduleDao.findById(schedule.getId());
////            Manager managerDb = managerDao.findByUsername(managerName);
//            ScheduleDTO scheduleDTO = new ScheduleDTO();
//            scheduleAllRespDB.ifPresent(x -> scheduleDTO.setId(x.getId()));
//            scheduleAllRespDB.ifPresent(x -> scheduleDTO.setName(x.getName()));
//            scheduleAllRespDB.ifPresent(x -> scheduleDTO.setScheduleDescription(x.getScheduleDescription()));
//            scheduleAllRespDB.ifPresent(x -> scheduleDTO.setTargetCustomer(x.getTargetCustomer()));
//            scheduleAllRespDB.ifPresent(x -> scheduleDTO.setEditable(x.getEditable()));
////            scheduleAllRespDB.ifPresent(x -> scheduleDTO.setManagerSchedule(x.getManagerSchedule()));
//            return scheduleDTO;
//        }).toList();
//    }
//    public Long generateRandomId() throws NoSuchAlgorithmException {
//        SecureRandom random = SecureRandom.getInstanceStrong();
//        Long userId = random.nextLong();
//        if(userId.longValue() < 0) return userId * -1;
//        return userId;
//    }
//
//    @Transactional
//    public Schedule updteSchdleToLnkWthMnger(Long idSchedule, Manager managerEntity) {
//        Optional<Schedule> scheduleAllRespDB = schduleDao.findById(idSchedule);
//        Schedule schedule = new Schedule();
//        scheduleAllRespDB.ifPresent(x -> schedule.setId(x.getId()));
//        scheduleAllRespDB.ifPresent(x -> schedule.setName(x.getName()));
//        scheduleAllRespDB.ifPresent(x -> schedule.setScheduleDescription(x.getScheduleDescription()));
//        scheduleAllRespDB.ifPresent(x -> schedule.setTargetCustomer(x.getTargetCustomer()));
//        scheduleAllRespDB.ifPresent(x -> schedule.setEditable(x.getEditable()));
////        scheduleAllRespDB.ifPresent(x -> schedule.linkScheduleToManager(managerEntity));
//        schduleDao.save(schedule);
//        return schedule;
//    }
//}
