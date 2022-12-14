package com.jaddy.calendarresourceoauth.model.dtos;

import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import com.jaddy.calendarresourceoauth.model.DayPlan;

import java.time.LocalDate;
import java.util.Date;

public class SchedulePlanDTO {

    private Long schedulePlanId;

    private LocalDate eventPlannerDate;

    private Schedule scheduleDetails;

    private Manager manager;

    private DayPlan monday;

    private DayPlan tuesday;

    private DayPlan wednesday;

    private DayPlan thursday;

    private DayPlan friday;

    private DayPlan saturday;

    private DayPlan sunday;

    public SchedulePlanDTO(){}

    public SchedulePlanDTO(Long schedulePlanId, Date scheduleStartOfWeek, DayPlan monday, Manager manager){}

    public Long getSchedulePlanId() {
        return schedulePlanId;
    }

    public void setSchedulePlanId(Long schedulePlanId) {
        this.schedulePlanId = schedulePlanId;
    }

    public LocalDate getScheduleStartOfWeek() {
        return eventPlannerDate;
    }

    public void setScheduleStartOfWeek(LocalDate eventPlannerDate) {
        this.eventPlannerDate = eventPlannerDate;
    }

    public Schedule getScheduleDetails() {
        return scheduleDetails;
    }

    public void setScheduleDetails(Schedule scheduleDetails) {
        this.scheduleDetails = scheduleDetails;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public DayPlan getMonday() {
        return monday;
    }

    public void setMonday(DayPlan monday) {
        this.monday = monday;
    }

    public DayPlan getTuesday() {
        return tuesday;
    }

    public void setTuesday(DayPlan tuesday) {
        this.tuesday = tuesday;
    }

    public DayPlan getWednesday() {
        return wednesday;
    }

    public void setWednesday(DayPlan wednesday) {
        this.wednesday = wednesday;
    }

    public DayPlan getThursday() {
        return thursday;
    }

    public void setThursday(DayPlan thursday) {
        this.thursday = thursday;
    }

    public DayPlan getFriday() {
        return friday;
    }

    public void setFriday(DayPlan friday) {
        this.friday = friday;
    }

    public DayPlan getSaturday() {
        return saturday;
    }

    public void setSaturday(DayPlan saturday) {
        this.saturday = saturday;
    }

    public DayPlan getSunday() {
        return sunday;
    }

    public void setSunday(DayPlan sunday) {
        this.sunday = sunday;
    }
}
