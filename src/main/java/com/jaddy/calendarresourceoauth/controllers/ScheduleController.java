package com.jaddy.calendarresourceoauth.controllers;

import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.model.DayPlan;
import com.jaddy.calendarresourceoauth.service.ScheduleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;


    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }
    @PostMapping("/schedule")
    public void saveSchedules(@RequestBody Schedule schedule, Principal principal){
        scheduleService.saveSchedule(schedule, principal.getName());
    }

}
