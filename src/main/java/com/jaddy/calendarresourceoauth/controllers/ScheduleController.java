package com.jaddy.calendarresourceoauth.controllers;

import com.jaddy.calendarresourceoauth.dao.ManagerDao;
import com.jaddy.calendarresourceoauth.dao.SchduleDao;
import com.jaddy.calendarresourceoauth.dao.SchedulePlanDao;
import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.ds.SchedulePlan;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import com.jaddy.calendarresourceoauth.model.dtos.ScheduleDTO;
import com.jaddy.calendarresourceoauth.service.SchedulePlanService;
import com.jaddy.calendarresourceoauth.service.ScheduleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
public class ScheduleController {

    private Logger logger = LogManager.getLogger(ScheduleController.class);
    private final ScheduleService scheduleService;

    private final ManagerDao managerDao;
    private final SchedulePlanService schedulePlanService;

    private final SchedulePlanDao schedulePlanDao;
    private final SchduleDao schduleDao;

    public ScheduleController(ScheduleService scheduleService, SchedulePlanService schedulePlanService, SchduleDao schduleDao, ManagerDao managerDao, SchedulePlanDao schedulePlanDao){
        this.scheduleService = scheduleService;
        this.schedulePlanService = schedulePlanService;
        this.schduleDao = schduleDao;
        this.managerDao = managerDao;
        this.schedulePlanDao = schedulePlanDao;
    }
    @PostAuthorize("hasAuthority('SCOPE_manager:create')")
    @PostMapping("/schedule")
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody Schedule schedule, Principal principal) throws RuntimeException, ParseException, NoSuchAlgorithmException {
        // calls the create Schedule method on scheduleservice
        Schedule testSchedule = scheduleService.saveSchedule(schedule, principal.getName());
        logger.info(testSchedule.getId());
        // calls the create Schedule Plan Method on scheduleplanner service
        SchedulePlan schedulePlan = schedulePlanService.createSchedulePlan(testSchedule.getId());
        schedulePlanDao.save(schedulePlan);
        // map the schedule Entity into the ScheduleDTO Object
        Optional<Schedule> scheduleRespDB = schduleDao.findById(testSchedule.getId());
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleRespDB.ifPresent(x -> scheduleDTO.setId(x.getId()));
        scheduleRespDB.ifPresent(x -> scheduleDTO.setName(x.getName()));
        scheduleRespDB.ifPresent(x -> scheduleDTO.setScheduleDescription(x.getScheduleDescription()));
        scheduleRespDB.ifPresent(x -> scheduleDTO.setTargetCustomer(x.getTargetCustomer()));
        scheduleRespDB.ifPresent(x -> scheduleDTO.setEditable(x.getEditable()));
        scheduleRespDB.ifPresent(x -> scheduleDTO.setManagerSchedule(x.getManagerSchedule()));
        logger.info(testSchedule.managerSchedule);

        // returns the scheduleDTO Object if Present
        return new ResponseEntity<>(scheduleDTO, HttpStatus.OK);
        }

    @PostAuthorize("hasAuthority('SCOPE_manager:read')")
    @GetMapping("/schedule")
    public List<ScheduleDTO> getAllSchedules() throws RuntimeException, ParseException, NoSuchAlgorithmException {
        return scheduleService.findAllSchedules();
    }
    @PostAuthorize("hasAuthority('SCOPE_manager:update')")
    @PutMapping("/schedule/{id_schedules_details}")
    public ResponseEntity<ScheduleDTO> updateScheduleAndLinkManager(@PathVariable("id_schedules_details") Long idSchedule, Principal principal){
        Manager manager = managerDao.findByUsername(principal.getName());
        Manager managerDTO = new Manager(manager.getId(), manager.getUsername());
        Schedule dbSchedule = scheduleService.updteSchdleToLnkWthMnger(idSchedule, managerDTO);
        ScheduleDTO scheduleDTO = new ScheduleDTO(dbSchedule.getId(), dbSchedule.getName(), dbSchedule.getScheduleDescription(), dbSchedule.getTargetCustomer(),
                dbSchedule.getEditable(), dbSchedule.managerSchedule);
        return new ResponseEntity<>(scheduleDTO, HttpStatus.OK);
    }


}
