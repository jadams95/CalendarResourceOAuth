package com.jaddy.calendarresourceoauth.ds;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import com.nimbusds.jose.shaded.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.DATE;

@Table(name = "`schedules`", schema = "public")
@Entity
public class Schedule extends BaseEntity {



    @Column(name = "`name`")
    private String name;
    @Column(name = "`description`")
    private String scheduleDescription;



    @Column(name = "`target`")
    private String targetCustomer;


    /**
     * After thinking about it you Will Click on the Calendar
     * make you input the times of hours and breaks for the  Schedule Plan for That week,
     * then it will direct you to Schedule
     */
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "schedule", cascade = CascadeType.ALL)
    private SchedulePlan schedulePlan;

    @JsonBackReference
    @ManyToMany
    @JoinTable(name = "schedule_managers", joinColumns = @JoinColumn(name = "id_schedules_details"), inverseJoinColumns = @JoinColumn(name = "uid_manager"))
    public List<Manager> managerSchedule = new ArrayList<>();


    private Boolean editable;

    public Schedule() {
    }

    public Schedule(String name, String scheduleDescription, String targetCustomer, Boolean editable) {
        this.name = name;
        this.scheduleDescription = scheduleDescription;
        this.targetCustomer = targetCustomer;
        this.editable = editable;
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



//    public SchedulePlan getScheduleDetailsId() {
//        return scheduleDetailsId;
//    }
//
//    public void setScheduleDetailsId(SchedulePlan scheduleDetailsId) {
//        this.scheduleDetailsId = scheduleDetailsId;
//    }

    public List<Manager> getManagerSchedule() {
        return managerSchedule;
    }

    public void linkScheduleToManager(Manager manager) {
        managerSchedule.add(manager);
    }

    public String getTargetCustomer() {
        return targetCustomer;
    }

    public void setTargetCustomer(String targetCustomer) {
        this.targetCustomer = targetCustomer;
    }

//    public SchedulePlan getSchedulePlanner() {
//        return schedulePlan;
//    }

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
