package com.jaddy.calendarresourceoauth.ds;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.jaddy.calendarresourceoauth.ds.users.Customer;
import com.jaddy.calendarresourceoauth.ds.users.Manager;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.DATE;
import static javax.persistence.TemporalType.TIME;

@Table(name = "schedules", schema = "public")
@Entity
public class Schedule extends BaseEntity {

    @OneToOne(mappedBy = "schedule", cascade = CascadeType.ALL)
    private SchedulePlan schedulePlan;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String scheduleDescription;

//    @Column(name = "duration")
//    private int duration;

    @Temporal(DATE)
    @Column(name = "schedule_start_of_week")
    private Date scheduleStartOfWeek;

    @Column(name = "target")
    private String targetCustomer;


    /**
     * After thinking about it you Will Click on the Calendar
     * make you input the times of hours and breaks for the  Schedule Plan for That week,
     * then it will direct you to Schedule
     */
//    @OneToOne()
//    @JoinColumn(name = "id_schedule_details")
//    private SchedulePlan scheduleDetailsId;
//


//    @JoinTable(name = "schedules_managers", joinColumns = @JoinColumn(name ="id_schedule"),
//            inverseJoinColumns = @JoinColumn(name = "id_manager"))

    @ManyToMany
    @JoinTable(name = "schedule_managers", joinColumns = @JoinColumn(name = "id_schedules_details"), inverseJoinColumns = @JoinColumn(name = "id_manager"))
    private List<Manager> managerSchedule;


    private Boolean editable;

    public Schedule() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScheduleDescription() {
        return scheduleDescription;
    }

    public void setScheduleDescription(String scheduleDescription) {
        this.scheduleDescription = scheduleDescription;
    }

//    public int getDuration() {
//        return duration;
//    }
//
//    public void setDuration(int duration) {
//        this.duration = duration;
//    }


    public Date getScheduleStartOfWeek() {
        return scheduleStartOfWeek;
    }

    public void setScheduleStartOfWeek(Date scheduleStartOfWeek) {
        this.scheduleStartOfWeek = scheduleStartOfWeek;
    }

//    public SchedulePlan getScheduleDetailsId() {
//        return scheduleDetailsId;
//    }
//
//    public void setScheduleDetailsId(SchedulePlan scheduleDetailsId) {
//        this.scheduleDetailsId = scheduleDetailsId;
//    }

    public List<Manager> getScheduleList() {
        return managerSchedule;
    }

    public void setManagerSchedule(List<Manager> managerSchedule) {
        this.managerSchedule = managerSchedule;
    }

    public String getTargetCustomer() {
        return targetCustomer;
    }

    public void setTargetCustomer(String targetCustomer) {
        this.targetCustomer = targetCustomer;
    }

//    public SchedulePlan getSchedulePlanner() {
//        return scheduleDetailsId;
//    }
//
//    public void setSchedulePlanner(SchedulePlan scheduleDetailsId) {
//        this.scheduleDetailsId = scheduleDetailsId;
//    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public SchedulePlan getSchedulePlan() {
        return schedulePlan;
    }

    public void setSchedulePlan(SchedulePlan schedulePlan) {
        this.schedulePlan = schedulePlan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;
        Schedule schedule = (Schedule) o;
        return super.getId().equals(schedule.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
