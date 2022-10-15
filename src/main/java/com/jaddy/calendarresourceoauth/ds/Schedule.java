package com.jaddy.calendarresourceoauth.ds;


import com.jaddy.calendarresourceoauth.ds.users.Manager;

import javax.persistence.*;
import java.util.List;

@Table(name = "schedules", schema = "public")
@Entity
public class Schedule extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String scheduleDescription;

    @Column(name = "duration")
    private int duration;

    @Column(name = "target")
    private String targetCustomer;


    @OneToOne()
    @JoinColumn(name = "id")
    private SchedulePlan scheuleDetailsId;



//    @JoinTable(name = "schedules_managers", joinColumns = @JoinColumn(name ="id_schedule"),
//            inverseJoinColumns = @JoinColumn(name = "id_manager"))



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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTargetCustomer() {
        return targetCustomer;
    }

    public void setTargetCustomer(String targetCustomer) {
        this.targetCustomer = targetCustomer;
    }

    public SchedulePlan getSchedulePlanner() {
        return scheuleDetailsId;
    }

    public void setSchedulePlanner(SchedulePlan scheuleDetailsId) {
        this.scheuleDetailsId = scheuleDetailsId;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
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
        return this.getId().hashCode();
    }
}
