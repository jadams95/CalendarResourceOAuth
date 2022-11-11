package com.jaddy.calendarresourceoauth.service;

import com.jaddy.calendarresourceoauth.constants.Role;
import com.jaddy.calendarresourceoauth.dao.AppointmentDao;
import com.jaddy.calendarresourceoauth.dao.CustomerDao;
import com.jaddy.calendarresourceoauth.dao.ManagerDao;
import com.jaddy.calendarresourceoauth.dao.SchduleDao;
import com.jaddy.calendarresourceoauth.ds.Appointment;
import com.jaddy.calendarresourceoauth.ds.AppointmentStatus;
import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.ds.users.Customer;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class AppointmentService {

    private final AppointmentDao appointmentDao;
    private final CustomerDao customerDao;

    private final ManagerDao managerDao;
    private final SchduleDao scheduleDao;

    public AppointmentService(AppointmentDao appointmentDao, CustomerDao customerDao, SchduleDao schduleDao, ManagerDao managerDao) {
        this.appointmentDao = appointmentDao;
        this.customerDao = customerDao;
        this.scheduleDao = schduleDao;
        this.managerDao = managerDao;
    }

    public void saveCustomerAppointment(Appointment appointment, Long scheduleId, String customerName){
        Appointment appointmentDb = new Appointment();
        Customer customerDB = customerDao.findByUsername(customerName);
//        Manager managerDB = scheduleDao.findSchedulesByManagerSchedule_Id(scheduleId);
        Optional<Schedule> scheduleDB = scheduleDao.findById(scheduleId);

        if(appointment == null){
            throw new RuntimeException("Appointment had an Error");
        }
        else {
            // Create the References to save
            appointmentDb.setStatus(AppointmentStatus.BOOKED);
            appointmentDb.setCustomer(customerDB);
            appointmentDb.setSchedule(scheduleDB.get());

            // Entity
            appointmentDb.setAppointmentScheduleStartTime(appointment.getAppointmentScheduleStartTime());
            appointmentDb.setAppointmentScheduleEndTime(appointment.getAppointmentScheduleEndTime());

            Customer customerEntity = new Customer(customerDB.getId(), customerDB.getUsername(), customerDB.getPassword(), Role.ROLE_CUSTOMER.name(), Role.ROLE_CUSTOMER.getAuthorities());
//            customerDao.saveAndFlush(customerEntity);

            Manager managerEntity = new Manager(scheduleDB.get().getScheduleList().getId(), scheduleDB.get().getScheduleList().getUsername(), scheduleDB.get().getScheduleList().getPassword(), scheduleDB.get().getScheduleList().getRole(), Role.ROLE_MANAGER.getAuthorities());
//            managerDao.saveAndFlush(managerEntity);

            appointmentDb.setCustomer(customerEntity);
            appointmentDb.setManager(managerEntity);
            appointmentDao.save(appointmentDb);
        }
    }
}
