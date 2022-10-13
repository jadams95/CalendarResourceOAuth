package com.jaddy.calendarresourceoauth.controllers;

import com.jaddy.calendarresourceoauth.dao.ManagerDao;
import com.jaddy.calendarresourceoauth.ds.SchedulePlan;
import com.jaddy.calendarresourceoauth.model.DayPlan;
import com.jaddy.calendarresourceoauth.service.SchedulePlanService;
import com.jaddy.calendarresourceoauth.service.authservices.ManagerTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/schedulePlan")
public class PlanSchedulerController {


    @Autowired
    private ManagerTokenService tokenService;

    private final SchedulePlanService schedulePlanService;

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private InMemoryUserDetailsManager userDetailsManager;

    public PlanSchedulerController(SchedulePlanService schedulePlanService) {
        this.schedulePlanService = schedulePlanService;
    }

//    @PostAuthorize("hasAuthority('manager:create')")
    @PostMapping("/monday")
    public void saveMondayWorkSchedule(@RequestBody DayPlan dayPlan, Principal principal){
        schedulePlanService.saveSchedulePlanWorkDayMonday(dayPlan, principal.getName());
    }
    @PostMapping("/tuesday/{id}")
    public void saveTuesdayWorkSchedule(@RequestBody DayPlan dayPlan, Principal principal){
        schedulePlanService.saveSchedulePlanWorkDayTuesday(dayPlan, principal.getName());
    }

    @PostMapping("/wednesday/{id}")
    public void saveWednesdayWorkSchedule(@RequestBody DayPlan dayPlan, Principal principal, @PathVariable("id") Long customerId){
        schedulePlanService.saveSchedulePlanWorkDayWednesday(dayPlan, principal.getName(), customerId);
    }

    @GetMapping("/{id}")
    public SchedulePlan displaySchedulePlanById(@PathVariable("id") Long id){
        return schedulePlanService.findSchedulePlanById(id);
    }


    @GetMapping("/{managerId}")
    public List<SchedulePlan> displayAllSchedulePlansByManagerId(@PathVariable("managerId") Long managerId){
//        return schedulePlanService
        return schedulePlanService.findSchedulesPlanByManagerId(managerId);
    }

    @GetMapping("")
    public ResponseEntity<List<SchedulePlan>> displayAllSchedulePlans(){
        return new ResponseEntity<>(schedulePlanService.findAllSchedulePlans(), HttpStatus.OK);
    }
}
