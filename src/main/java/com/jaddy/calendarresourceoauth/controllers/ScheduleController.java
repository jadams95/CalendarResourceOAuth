package com.jaddy.calendarresourceoauth.controllers;

import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.ds.SchedulePlan;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import com.jaddy.calendarresourceoauth.service.SchedulePlanService;
import com.jaddy.calendarresourceoauth.service.ScheduleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.text.ParseException;
import java.util.Arrays;

@RestController
public class ScheduleController {

    private Logger logger = LogManager.getLogger(ScheduleController.class);
    private final ScheduleService scheduleService;
    private final SchedulePlanService schedulePlanService;


    public ScheduleController(ScheduleService scheduleService, SchedulePlanService schedulePlanService){
        this.scheduleService = scheduleService;
        this.schedulePlanService = schedulePlanService;
    }
    @PostAuthorize("hasAuthority('SCOPE_manager:create')")
    @PostMapping("/schedule")
    public Schedule createSchedule(@RequestBody Schedule schedule, Principal principal) throws RuntimeException, ParseException, NoSuchAlgorithmException {
//            SchedulePlan schedulePlan = schedulePlanService.createSchedulePlan(id, principal.getName());

//            schedule.setManagerSchedule(Arrays.asList(schedulePlan.getManager()));
//           SchedulePlan schedulePlanDB = schedulePlanService.createSchedulePlan(principal.getName());
//            logger.info(schedulePlanDB);
            Schedule testSchedule = scheduleService.saveSchedule(schedule, principal.getName());
            schedulePlanService.createSchedulePlan(testSchedule.getId());
            return testSchedule;
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
