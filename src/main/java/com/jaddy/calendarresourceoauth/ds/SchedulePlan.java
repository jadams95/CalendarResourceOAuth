package com.jaddy.calendarresourceoauth.ds;

import com.fasterxml.jackson.databind.JsonNode;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import com.jaddy.calendarresourceoauth.model.DayPlan;
import com.jaddy.calendarresourceoauth.model.TimePeriod;
import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;

@TypeDefs(@TypeDef(typeClass = JsonType.class, defaultForType = JsonNode.class))
@Entity
@Table(name = "schedule_plans", schema = "public")
public class SchedulePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(DATE)
    @Column(name = "schedule_start_of_week")
    private Date scheduleStartOfWeek;





    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_manager")
    private Manager manager;


//    @Temporal(TemporalType.DATE)

//    @Column(name = "start_work_week")
//    private LocalDate startScheduleDate;



    @Type(type = "json")
    @Column(columnDefinition = "json", name = "monday")
    private DayPlan monday;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "tuesday")
    private DayPlan tuesday;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "wednesday")
    private DayPlan wednesday;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "thursday")
    private DayPlan thursday;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "friday")
    private DayPlan friday;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "saturday")
    private DayPlan saturday;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "sunday")
    private DayPlan sunday;


    public SchedulePlan() {
    }

//    public LocalDate getStartScheduleDate() {
//        return startScheduleDate;
//    }
//
//    public void setStartScheduleDate(LocalDate startScheduleDate) {
//        this.startScheduleDate = startScheduleDate;
//    }

    public Date getScheduleStartOfWeek() {
        return scheduleStartOfWeek;
    }

    public void setScheduleStartOfWeek(Date scheduleStartOfWeek) {
        this.scheduleStartOfWeek = scheduleStartOfWeek;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public DayPlan getDay(String day) {
        switch (day) {
            case "monday":
                return monday;

            case "tuesday":
                return tuesday;

            case "wednesday":
                return wednesday;

            case "thursday":
                return thursday;

            case "friday":
                return friday;

            case "saturday":
                return saturday;

            case "sunday":
                return sunday;

            default:
                return null;
        }
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static SchedulePlan generateDefaultWorkingPlan() {
        SchedulePlan wp = new SchedulePlan();
        LocalTime defaultStartHour = LocalTime.parse("06:00");
        LocalTime defaultEndHour = LocalTime.parse("18:00");
        TimePeriod defaultWorkingPeriod = new TimePeriod(defaultStartHour, defaultEndHour);
        DayPlan defaultDayPlan = new DayPlan(defaultWorkingPeriod);
        wp.setMonday(defaultDayPlan);
        wp.setTuesday(defaultDayPlan);
        wp.setWednesday(defaultDayPlan);
        wp.setThursday(defaultDayPlan);
        wp.setFriday(defaultDayPlan);
        wp.setSaturday(defaultDayPlan);
        wp.setSunday(defaultDayPlan);
        return wp;
    }
}
