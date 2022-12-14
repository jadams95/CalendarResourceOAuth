package com.jaddy.calendarresourceoauth.controllers;

import com.jaddy.calendarresourceoauth.dao.ManagerDao;
import com.jaddy.calendarresourceoauth.dao.SchduleDao;
import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.ds.SchedulePlan;
import com.jaddy.calendarresourceoauth.model.DayPlan;
//import com.jaddy.calendarresourceoauth.service.SchedulePlanService;
import com.jaddy.calendarresourceoauth.model.dtos.ScheduleDTO;
import com.jaddy.calendarresourceoauth.model.dtos.SchedulePlanDTO;
import com.jaddy.calendarresourceoauth.service.SchedulePlanService;
import com.jaddy.calendarresourceoauth.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
public class PlanSchedulerController {



    private final SchduleDao scheduleDao;

    private final SchedulePlanService schedulePlanService;

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private JdbcUserDetailsManager userDetailsManager;

    public PlanSchedulerController(SchedulePlanService schedulePlanService, SchduleDao scheduleDao) {
        this.schedulePlanService = schedulePlanService;
        this.scheduleDao = scheduleDao;
    }

//    @PostAuthorize("hasAuthority('SCOPE_manager:update')")
    @PutMapping("/schedule/{scheduleId}/monday/{id}")
    public ResponseEntity<SchedulePlan> updateMondaySchedulePlan(@PathVariable("scheduleId") Long scheduleId, @PathVariable("id") Long schedulePlanId, @RequestBody DayPlan dayPlan, Principal principal){
        SchedulePlan testSchedulePlan = schedulePlanService.updateSchedulePlanWorkDayMonday(schedulePlanId, scheduleId, dayPlan, principal.getName());
//        Optional<Schedule> testSchedule = scheduleDao.findById(scheduleId);
//        Schedule testExample = testSchedule.orElseThrow();

        SchedulePlan schedulePlan = new SchedulePlan();
        schedulePlan.setId(testSchedulePlan.getId());
        schedulePlan.setMonday(testSchedulePlan.getMonday());
//        schedulePlan.setSchedule(testExample);
        return new ResponseEntity<>(schedulePlan, HttpStatus.OK);
    }
//    @PostAuthorize("hasAuthority('SCOPE_manager:update')")
//    @PutMapping("/schedule/{scheduleId}/tuesday/{id}")
//    public ResponseEntity<SchedulePlan> updateTuesdaySchedulePlan(@PathVariable("id") Long schedulePlanId, @RequestBody DayPlan dayPlan, Principal principal){
//        SchedulePlan testSchedulePlan = schedulePlanService.updateSchedulePlanWorkDayTuesday(schedulePlanId, dayPlan, principal.getName());
//        SchedulePlan schedulePlan = new SchedulePlan();
//        schedulePlan.setId(testSchedulePlan.getId());
//        schedulePlan.setMonday(testSchedulePlan.getMonday());
//        schedulePlan.setTuesday(testSchedulePlan.getTuesday());
//        return new ResponseEntity<>(schedulePlan, HttpStatus.OK);
//    }
//
////    @PostAuthorize("hasAuthority('SCOPE_manager:update')")
//    @PutMapping("/schedule/{scheduleId}/wednesday/{id}")
//    public ResponseEntity<SchedulePlan> updateWednesdaySchedulePlan(@PathVariable("id") Long schedulePlanId, @RequestBody DayPlan dayPlan, Principal principal){
//        SchedulePlan testSchedulePlan = schedulePlanService.updateSchedulePlanWorkDayWednesday(schedulePlanId, dayPlan, principal.getName());
//        SchedulePlan schedulePlan = new SchedulePlan();
//        schedulePlan.setId(testSchedulePlan.getId());
//        schedulePlan.setMonday(testSchedulePlan.getMonday());
//        schedulePlan.setTuesday(testSchedulePlan.getTuesday());
//        schedulePlan.setWednesday(testSchedulePlan.getWednesday());
//        return new ResponseEntity<>(schedulePlan, HttpStatus.OK);
//    }
//
////    @PostAuthorize("hasAuthority('SCOPE_manager:update')")
//    @PutMapping("/schedule/{scheduleId}/thursday/{id}")
//    public ResponseEntity<SchedulePlan> updateThursdaySchedulePlan(@PathVariable("id") Long schedulePlanId, @RequestBody DayPlan dayPlan, Principal principal){
//        SchedulePlan testSchedulePlan = schedulePlanService.updateSchedulePlanWorkDayThursday(schedulePlanId, dayPlan, principal.getName());
//        SchedulePlan schedulePlan = new SchedulePlan();
//        schedulePlan.setId(testSchedulePlan.getId());
//        schedulePlan.setMonday(testSchedulePlan.getMonday());
//        schedulePlan.setTuesday(testSchedulePlan.getTuesday());
//        schedulePlan.setWednesday(testSchedulePlan.getWednesday());
//        schedulePlan.setThursday(testSchedulePlan.getThursday());
//        return new ResponseEntity<>(schedulePlan, HttpStatus.OK);
//    }
////    @PostAuthorize("hasAuthority('SCOPE_manager:update')")
//    @PutMapping("/schedule/{scheduleId}/friday/{id}")
//    public ResponseEntity<SchedulePlan> updateFridaySchedulePlan(@PathVariable("id") Long schedulePlanId, @RequestBody DayPlan dayPlan, Principal principal){
//        SchedulePlan testSchedulePlan = schedulePlanService.updateSchedulePlanWorkDayFriday(schedulePlanId, dayPlan, principal.getName());
//        SchedulePlan schedulePlan = new SchedulePlan();
//        schedulePlan.setId(testSchedulePlan.getId());
//        schedulePlan.setMonday(testSchedulePlan.getMonday());
//        schedulePlan.setTuesday(testSchedulePlan.getTuesday());
//        schedulePlan.setWednesday(testSchedulePlan.getWednesday());
//        schedulePlan.setThursday(testSchedulePlan.getThursday());
//        schedulePlan.setFriday(testSchedulePlan.getFriday());
//        return new ResponseEntity<>(schedulePlan, HttpStatus.OK);
//    }
////    @PostAuthorize("hasAuthority('SCOPE_manager:update')")
//    @PutMapping("/saturday/{id}")
//    public ResponseEntity<SchedulePlan> updateSaturdaySchedulePlan(@PathVariable("id") Long schedulePlanId, @RequestBody DayPlan dayPlan, Principal principal){
//        SchedulePlan testSchedulePlan = schedulePlanService.updateSchedulePlanWorkDaySaturday(schedulePlanId, dayPlan, principal.getName());
//        SchedulePlan schedulePlan = new SchedulePlan();
//        schedulePlan.setId(testSchedulePlan.getId());
//        schedulePlan.setMonday(testSchedulePlan.getMonday());
//        schedulePlan.setTuesday(testSchedulePlan.getTuesday());
//        schedulePlan.setWednesday(testSchedulePlan.getWednesday());
//        schedulePlan.setThursday(testSchedulePlan.getThursday());
//        schedulePlan.setFriday(testSchedulePlan.getFriday());
//        schedulePlan.setSaturday(testSchedulePlan.getSaturday());
//        return new ResponseEntity<>(schedulePlan, HttpStatus.OK);
//    }
//
////    @PostAuthorize("hasAuthority('SCOPE_manager:update')")
//    @PutMapping("/sunday/{id}")
//    public ResponseEntity<SchedulePlan> updateSundaySchedulePlan(@PathVariable("id") Long schedulePlanId, @RequestBody DayPlan dayPlan, Principal principal){
//        SchedulePlan testSchedulePlan = schedulePlanService.updateSchedulePlanWorkDaySunday(schedulePlanId, dayPlan, principal.getName());
//        SchedulePlan schedulePlan = new SchedulePlan();
//        schedulePlan.setId(testSchedulePlan.getId());
//        schedulePlan.setMonday(testSchedulePlan.getMonday());
//        schedulePlan.setTuesday(testSchedulePlan.getTuesday());
//        schedulePlan.setWednesday(testSchedulePlan.getWednesday());
//        schedulePlan.setThursday(testSchedulePlan.getThursday());
//        schedulePlan.setFriday(testSchedulePlan.getFriday());
//        schedulePlan.setSaturday(testSchedulePlan.getSaturday());
//        schedulePlan.setSunday(testSchedulePlan.getSunday());
//        return new ResponseEntity<>(schedulePlan, HttpStatus.OK);
//    }

//    @PostAuthorize("hasAuthority('SCOPE_manager:read')")
    @GetMapping("/schedule/schedulePlanners")
    public List<SchedulePlanDTO> getAllSchedules() throws RuntimeException, ParseException, NoSuchAlgorithmException {
        return schedulePlanService.findAllSchedulePlans();
    }

// The idea was to be able to filter SchedulePlans that are assigned to id_manager, but
// implemented first a simple getById for the SchedulePlan
//    @GetMapping("/{id}")
//    public SchedulePlan displaySchedulePlanById(@PathVariable("id") Long id){
//        return schedulePlanService.findSchedulePlanById(id);
//    }

//    @PostAuthorize("hasAuthority('SCOPE_manager:read')")
//    @GetMapping("/")
//    public ResponseEntity<List<SchedulePlan>> displayAllSchedulePlans(){
//        return new ResponseEntity<>(schedulePlanService.findAllSchedulePlans(), HttpStatus.OK);
//    }
}
