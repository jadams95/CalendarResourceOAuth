package com.jaddy.calendarresourceoauth.controllers;

import com.jaddy.calendarresourceoauth.dao.ManagerDao;
import com.jaddy.calendarresourceoauth.ds.SchedulePlan;
import com.jaddy.calendarresourceoauth.model.DayPlan;
import com.jaddy.calendarresourceoauth.service.SchedulePlanService;
import com.jaddy.calendarresourceoauth.service.authservices.ManagerTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    public void manageMondayWorkSchedule(@RequestBody DayPlan dayPlan, Principal principal){
//        DayPlan dayPlan = new DayPlan(schedulePlan.getMonday().getWorkingHours());
//        schedulePlanModel.setTimePeriod(schedulePlanModel.getTimePeriod());

//        schedulePlanModel.setDayPlan(dayPlan);
//        schedulePlanModel.setStartOfSchedule(schedulePlanModel.getStartOfSchedule());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//        LocalDate startOfWorkWeek = dayPlan.getStartOfSchedule();

        schedulePlanService.saveSchedulePlanWorkDayMonday(dayPlan, principal.getName());
    }
}
