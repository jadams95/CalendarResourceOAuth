package com.jaddy.calendarresourceoauth.service;

import com.jaddy.calendarresourceoauth.dao.AppointmentDao;
import com.jaddy.calendarresourceoauth.dao.CustomerDao;
import com.jaddy.calendarresourceoauth.dao.SchduleDao;
import com.jaddy.calendarresourceoauth.ds.Appointment;
import com.jaddy.calendarresourceoauth.ds.users.Customer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentDao appointmentDao;
    private final CustomerDao customerDao;

    private final SchduleDao scheduleDao;

    public AppointmentService(AppointmentDao appointmentDao, CustomerDao customerDao, SchduleDao schduleDao) {
        this.appointmentDao = appointmentDao;
        this.customerDao = customerDao;
        this.scheduleDao = schduleDao;
    }

    public void saveCustomerAppointment(Appointment appointment, Long customerId){
        Appointment appointmentDb = new Appointment();
        Optional<Customer> customerDB = customerDao.findById(customerId);
        if(appointment == null){
            throw new RuntimeException("Appointment had an Error");
        }
        else {
            appointmentDb.setSchedule(appointment.getSchedule());
            appointmentDb.setCustomer(customerDB.get());
            appointmentDb.setAppointmentScheduleStartTime(appointment.getAppointmentScheduleStartTime());
            appointmentDb.setAppointmentScheduleEndTime(appointment.getAppointmentScheduleEndTime());
        }
    }
}
