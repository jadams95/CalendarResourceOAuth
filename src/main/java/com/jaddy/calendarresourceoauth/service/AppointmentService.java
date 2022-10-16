package com.jaddy.calendarresourceoauth.service;

import com.jaddy.calendarresourceoauth.dao.AppointmentDao;
import com.jaddy.calendarresourceoauth.dao.CustomerDao;
import com.jaddy.calendarresourceoauth.dao.ManagerDao;
import com.jaddy.calendarresourceoauth.dao.SchduleDao;
import com.jaddy.calendarresourceoauth.ds.Appointment;
import com.jaddy.calendarresourceoauth.ds.AppointmentStatus;
import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.ds.users.Customer;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Manager managerDB = managerDao.findManagerBySchedules(scheduleId);
        Optional<Schedule> scheduleDB = scheduleDao.findById(scheduleId);
        if(appointment == null){
            throw new RuntimeException("Appointment had an Error");
        }
        else {
            // Create the References to save

            appointmentDb.setStatus(AppointmentStatus.BOOKED);
            appointmentDb.setCustomer(customerDB);
            appointmentDb.setManager(managerDB);
            appointmentDb.setSchedule(scheduleDB.get());

            // Entity
            appointmentDb.setAppointmentScheduleStartTime(appointment.getAppointmentScheduleStartTime());
            appointmentDb.setAppointmentScheduleEndTime(appointment.getAppointmentScheduleEndTime());

            Customer customerEntity = new Customer();
            customerEntity.setAppointments(List.of(appointmentDb));
            customerDao.save(customerEntity);

            Manager managerEntity = new Manager();
            managerEntity.setAppointments(List.of(appointmentDb));
            managerDao.save(managerEntity);
            appointmentDao.save(appointmentDb);
        }
    }
}
