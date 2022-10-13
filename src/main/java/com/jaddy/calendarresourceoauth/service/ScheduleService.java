package com.jaddy.calendarresourceoauth.service;


import com.jaddy.calendarresourceoauth.dao.ManagerDao;
import com.jaddy.calendarresourceoauth.dao.SchduleDao;
import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final SchduleDao schduleDao;
    private final ManagerDao managerDao;

    public ScheduleService(SchduleDao schduleDao, ManagerDao managerDao){
        this.schduleDao = schduleDao;
        this.managerDao = managerDao;
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

    public Schedule saveScheduleMonday(Schedule scheduleEntity, String userName){
        Schedule scheduleDb = new Schedule();
        Manager dbManager = managerDao.findByUsername(userName);
        if(scheduleEntity == null){
            throw new RuntimeException(" User Cannot be loaded");
        } else {
//            scheduleDb.setId(scheduleEntity.getId());
            scheduleDb.setName(scheduleEntity.getName());
            scheduleDb.setDuration(scheduleEntity.getDuration());
            scheduleDb.setScheduleDescription(scheduleEntity.getScheduleDescription());
            scheduleDb.setManagerList(List.of(dbManager));
            scheduleDb.setEditable(scheduleEntity.getEditable());
            scheduleDb.setTargetCustomer(scheduleEntity.getTargetCustomer());
//            schedulePlan.setMonday(schedulePlanModel);
            schduleDao.save(scheduleDb);
            return scheduleDb;
        }
    }

}
