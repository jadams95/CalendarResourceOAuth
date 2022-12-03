package com.jaddy.calendarresourceoauth.ds;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jaddy.calendarresourceoauth.ds.users.Customer;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import com.jaddy.calendarresourceoauth.model.AppointmentSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "`appointments`", schema = "public")
@JsonSerialize(using = AppointmentSerializer.class)
public class Appointment extends BaseEntity implements Comparable<Appointment> {
    @Column(name = "`appointment_start`")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime appointmentScheduleStartTime;
    @Column(name = "`appointment_end`")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime appointmentScheduleEndTime;
    @Column(name = "`canceled_at`")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime appointmentCanceledTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "`status`")
    private AppointmentStatus status;

    @ManyToOne
    @JoinColumn(name = "`uid_manager`")
    private Manager manager;

    @ManyToOne
    @JoinColumn(name="`id_customer`")
    private Customer customer;


    @ManyToOne
    @JoinColumn(name="`id_schedule`")
    private Schedule schedule;

    public Appointment() {
    }


    public Appointment(LocalDateTime appointmentScheduleStartTime, LocalDateTime appointmentScheduleEndTime, Manager manager, Customer customer, Schedule schedule) {
        this.appointmentScheduleStartTime = appointmentScheduleStartTime;
        this.appointmentScheduleEndTime = appointmentScheduleEndTime;
        this.manager = manager;
        this.customer = customer;
        this.schedule = schedule;
    }

    @Override
    public int compareTo(Appointment o) {
        return this.getAppointmentScheduleStartTime().compareTo(o.getAppointmentScheduleStartTime());
    }

    public LocalDateTime getAppointmentScheduleStartTime() {
        return appointmentScheduleStartTime;
    }

    public void setAppointmentScheduleStartTime(LocalDateTime appointmentScheduleStartTime) {
        this.appointmentScheduleStartTime = appointmentScheduleStartTime;
    }

    public LocalDateTime getAppointmentScheduleEndTime() {
        return appointmentScheduleEndTime;
    }

    public void setAppointmentScheduleEndTime(LocalDateTime appointmentScheduleEndTime) {
        this.appointmentScheduleEndTime = appointmentScheduleEndTime;
    }

    public LocalDateTime getAppointmentCanceledTime() {
        return appointmentCanceledTime;
    }

    public void setAppointmentCanceledTime(LocalDateTime appointmentCanceledTime) {
        this.appointmentCanceledTime = appointmentCanceledTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
