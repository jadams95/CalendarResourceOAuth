package com.jaddy.calendarresourceoauth.controllers;

import com.jaddy.calendarresourceoauth.ds.Appointment;
import com.jaddy.calendarresourceoauth.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;
    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @PostMapping("/appointment/{id}")
    public ResponseEntity<?> saveAppointment(@RequestBody Appointment appointmentEntity, @PathVariable("id") Long scheduleId, Authentication authentication) throws NoSuchAlgorithmException {
        if(appointmentEntity != null) {
            appointmentService.saveCustomerAppointment(appointmentEntity, scheduleId, authentication.getName());
            return new ResponseEntity<>(appointmentEntity, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
