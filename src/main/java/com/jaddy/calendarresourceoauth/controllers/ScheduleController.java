package com.jaddy.calendarresourceoauth.controllers;

import com.jaddy.calendarresourceoauth.dao.ManagerDao;
import com.jaddy.calendarresourceoauth.dao.SchduleDao;
import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.ds.SchedulePlan;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import com.jaddy.calendarresourceoauth.model.dtos.ManagerDTO;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ScheduleController {

    private Logger logger = LogManager.getLogger(ScheduleController.class);
    private final ScheduleService scheduleService;

    private final ManagerDao managerDao;
    private final SchedulePlanService schedulePlanService;

    private final SchduleDao schduleDao;

    public ScheduleController(ScheduleService scheduleService, SchedulePlanService schedulePlanService, SchduleDao schduleDao, ManagerDao managerDao){
        this.scheduleService = scheduleService;
        this.schedulePlanService = schedulePlanService;
        this.schduleDao = schduleDao;
        this.managerDao = managerDao;
    }
    @PostAuthorize("hasAuthority('SCOPE_manager:create')")
    @PostMapping("/schedule")
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody Schedule schedule, Principal principal) throws RuntimeException, ParseException, NoSuchAlgorithmException {
        Schedule testSchedule = scheduleService.saveSchedule(schedule, principal.getName());
        schedulePlanService.createSchedulePlan(testSchedule.getId());
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        Optional<Schedule> scheduleRespDB = schduleDao.findById(testSchedule.getId());
        scheduleRespDB.ifPresent(x -> scheduleDTO.setId(x.getId()));
        scheduleRespDB.ifPresent(x -> scheduleDTO.setName(x.getName()));
        scheduleRespDB.ifPresent(x -> scheduleDTO.setScheduleDescription(x.getScheduleDescription()));
        scheduleRespDB.ifPresent(x -> scheduleDTO.setTargetCustomer(x.getTargetCustomer()));
        scheduleRespDB.ifPresent(x -> scheduleDTO.setEditable(x.getEditable()));
        return new ResponseEntity<>(scheduleDTO, HttpStatus.OK);
        }

    @PostAuthorize("hasAuthority('SCOPE_manager:read')")
    @GetMapping("/schedule")
    public List<ScheduleDTO> getAllSchedules() throws RuntimeException, ParseException, NoSuchAlgorithmException {
        return scheduleService.findAllSchedules();
    }
    @PostAuthorize("hasAuthority('SCOPE_manager:update')")
    @PutMapping("/schedule/{id_schedule}")
    public ResponseEntity<ScheduleDTO> updateScheduleAndLinkManager(@PathVariable("id_schedule") Long idSchedule, Principal principal){
        Manager manager = managerDao.findByUsername(principal.getName());
//        ManagerDTO managerDTO = new ManagerDTO(manager.getId(), manager.getUsername());
        Schedule dbSchedule = scheduleService.updteSchdleToLnkWthMnger(idSchedule, manager);
        ScheduleDTO scheduleDTO = new ScheduleDTO(dbSchedule.getId(), dbSchedule.getName(), dbSchedule.getScheduleDescription(), dbSchedule.getTargetCustomer(), dbSchedule.getEditable(), dbSchedule.getManagerSchedule());
        return new ResponseEntity<>(scheduleDTO, HttpStatus.OK);
    }


}
