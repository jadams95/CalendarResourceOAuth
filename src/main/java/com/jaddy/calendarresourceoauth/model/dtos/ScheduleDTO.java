package com.jaddy.calendarresourceoauth.model.dtos;

import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.ds.SchedulePlan;
import com.jaddy.calendarresourceoauth.ds.users.Manager;

import java.util.Date;
import java.util.List;

public class ScheduleDTO extends Schedule {
    private Long id;
    private String name;
    private String scheduleDescription;
    private String targetCustomer;
    private Boolean editable;

    public List<Manager> managerSchedule;

    public SchedulePlan schedulePlan;

    public ScheduleDTO() {
    }

    public ScheduleDTO(Long id, String name, String scheduleDescription, String targetCustomer, Boolean editable) {
        this.id = id;
        this.name = name;
        this.scheduleDescription = scheduleDescription;
        this.targetCustomer = targetCustomer;
        this.editable = editable;
    }
    public ScheduleDTO(Long id, String name, String scheduleDescription, String targetCustomer, Boolean editable, List<Manager> managerSchedule) {
        this.id = id;
        this.name = name;
        this.scheduleDescription = scheduleDescription;
        this.targetCustomer = targetCustomer;
        this.editable = editable;
        this.managerSchedule = managerSchedule;
    }

    public ScheduleDTO(Long id, String name, String scheduleDescription, String targetCustomer, Boolean editable, List<Manager> managerSchedule, SchedulePlan schedulePlan) {
        this.id = id;
        this.name = name;
        this.scheduleDescription = scheduleDescription;
        this.targetCustomer = targetCustomer;
        this.editable = editable;
        this.managerSchedule = managerSchedule;
        this.schedulePlan = schedulePlan;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getScheduleDescription() {
        return super.getScheduleDescription();
    }

    @Override
    public void setScheduleDescription(String scheduleDescription) {
        super.setScheduleDescription(scheduleDescription);
    }

    public void setManagerSchedule(List<Manager> managerSchedule) {
        this.managerSchedule = managerSchedule;
    }

    @Override
    public SchedulePlan getSchedulePlan() {
        return schedulePlan;
    }

    @Override
    public void setSchedulePlan(SchedulePlan schedulePlan) {
        this.schedulePlan = schedulePlan;
    }

    @Override
    public String getTargetCustomer() {
        return super.getTargetCustomer();
    }

    @Override
    public void setTargetCustomer(String targetCustomer) {
        super.setTargetCustomer(targetCustomer);
    }

    @Override
    public Boolean getEditable() {
        return super.getEditable();
    }

    @Override
    public void setEditable(Boolean editable) {
        super.setEditable(editable);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


    public List<Manager> getManagerSchedule() {
        return managerSchedule;
    }

    public void linkScheduleToManager(Manager manager) {
        this.managerSchedule.add(manager);
    }
}
