package com.jaddy.calendarresourceoauth.controllers;

import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;


    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }
    @PostAuthorize("hasAuthority('SCOPE_manager:create')")
    @PostMapping("/schedule/{id}")
    public ResponseEntity<?> saveSchedules(@PathVariable("id") Long id, @RequestBody Schedule schedule, Authentication authentication) throws RuntimeException, ParseException {
        if(schedule.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        if(authentication.getAuthorities().contains("customer:create")) return new ResponseEntity<>("User is unauthorized for request" + authentication.getName(), HttpStatus.UNAUTHORIZED);
//        if(authentication.getAuthorities().contains(Arrays.stream(Role.ROLE_CUSTOMER.getAuthorities()).filter(x -> x.startsWith("customer")))) return new ResponseEntity<>("User is not authorized for request", HttpStatus.FORBIDDEN);
//        if (authentication.getName() != null) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(scheduleService.saveSchedule(schedule, id, authentication.getName()), HttpStatus.OK);
    }

//    @PostAuthorize("hasAuthority('SCOPE_manager:create')")
//    @PostMapping("/schedule")
//    public ResponseEntity<Schedule> saveSchedules(@RequestBody Schedule schedule) throws RuntimeException{
//        if(schedule != null) {
//            scheduleService.saveJustSchedule(schedule);
//            return new ResponseEntity<>(schedule, HttpStatus.OK);
//        }
////        if(authentication.getAuthorities().contains("customer:create")) return new ResponseEntity<>("User is unauthorized for request" + authentication.getName(), HttpStatus.UNAUTHORIZED);
////        if(authentication.getAuthorities().contains(Arrays.stream(Role.ROLE_CUSTOMER.getAuthorities()).filter(x -> x.startsWith("customer")))) return new ResponseEntity<>("User is not authorized for request", HttpStatus.FORBIDDEN);
////        if (authentication.getName() != null) return new ResponseEntity<>(HttpStatus.OK);
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }


}
