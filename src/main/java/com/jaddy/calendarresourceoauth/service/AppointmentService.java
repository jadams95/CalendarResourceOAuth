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

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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

    public void saveCustomerAppointment(Appointment appointment, Long scheduleId, String customerName) throws NoSuchAlgorithmException {
        Appointment appointmentDb = new Appointment();
        Customer customerDB = customerDao.findByUsername(customerName);
//        Manager managerDB = scheduleDao;
        Optional<Manager> managerDB = managerDao.findById(Long.valueOf(2697));
        Optional<Schedule> scheduleDB = scheduleDao.findById(scheduleId);

        if(appointment == null){
            throw new RuntimeException("Appointment had an Error");
        }
        else {
            // Create the References to save
            appointmentDb.setId(generateRandomId());
            appointmentDb.setStatus(AppointmentStatus.BOOKED);
//            appointmentDb.setCustomer(customerDB);
//            appointmentDb.setSchedule(scheduleDB.get());

            // Entity
            appointmentDb.setAppointmentScheduleStartTime(appointment.getAppointmentScheduleStartTime());
            appointmentDb.setAppointmentScheduleEndTime(appointment.getAppointmentScheduleEndTime());

            Customer customerEntity = new Customer(customerDB.getId(), customerDB.getUsername());
            Manager managerEntity = new Manager();
            Schedule scheduleEntity = new Schedule();

            managerDB.ifPresent(x -> managerEntity.setId(x.getId()));
            managerDB.ifPresent(x -> managerEntity.setUsername(x.getUsername()));

            scheduleDB.ifPresent(x -> scheduleEntity.setId(x.getId()));
            scheduleDB.ifPresent(x -> scheduleEntity.setName(x.getName()));
            scheduleDB.ifPresent(x -> scheduleEntity.setScheduleDescription(x.getScheduleDescription()));
            scheduleDB.ifPresent(x -> scheduleEntity.setEditable(x.getEditable()));
            scheduleDB.ifPresent(x -> scheduleEntity.setTargetCustomer(x.getTargetCustomer()));
//            scheduleDB.ifPresent(x -> );
            managerDao.save(managerEntity);

//            appointmentDb.setManager(managerEntity);
//            Manager managerEntity = new Manager(2697, , scheduleDB.get().getScheduleList().getPassword(), scheduleDB.get().getScheduleList().getRole(), Role.ROLE_MANAGER.getAuthorities());
//            managerDao.saveAndFlush(managerEntity);
            // Need to set the Many to Many Mapping
            appointmentDb.setCustomer(customerEntity);
            appointmentDb.setManager(managerEntity);
            appointmentDb.setSchedule(scheduleEntity);
            appointmentDao.save(appointmentDb);
        }
    }
    public Long generateRandomId() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstanceStrong();
        Long userId = random.nextLong();
        if(userId.longValue() < 0) return userId * -1;
        return userId;
    }
}
