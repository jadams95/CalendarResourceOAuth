package com.jaddy.calendarresourceoauth.controllers;

import com.jaddy.calendarresourceoauth.constants.Role;
import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.model.DayPlan;
import com.jaddy.calendarresourceoauth.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.Subject;
import java.net.URI;
import java.security.Principal;
import java.util.Arrays;

@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;


    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }
    @PostAuthorize("hasAuthority('SCOPE_manager:create')")
    @PostMapping("/schedule")
    public ResponseEntity<?> saveSchedules(@RequestBody Schedule schedule) throws RuntimeException{
        if(schedule != null) {
            scheduleService.saveSchedule(schedule);
            return new ResponseEntity<>(schedule, HttpStatus.OK);
        }
//        if(authentication.getAuthorities().contains("customer:create")) return new ResponseEntity<>("User is unauthorized for request" + authentication.getName(), HttpStatus.UNAUTHORIZED);
//        if(authentication.getAuthorities().contains(Arrays.stream(Role.ROLE_CUSTOMER.getAuthorities()).filter(x -> x.startsWith("customer")))) return new ResponseEntity<>("User is not authorized for request", HttpStatus.FORBIDDEN);
//        if (authentication.getName() != null) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
