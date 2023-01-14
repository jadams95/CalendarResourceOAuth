package com.jaddy.calendarresourceoauth.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jaddy.calendarresourceoauth.ds.users.Manager;
import com.jaddy.calendarresourceoauth.model.DayPlan;
import com.jaddy.calendarresourceoauth.model.TimePeriod;
import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.DATE;

@TypeDefs(@TypeDef( name = "json", typeClass = JsonType.class, defaultForType = JsonType.class))
@Entity
@Table(name = "schedule_plans", schema = "public")
public class SchedulePlan  {

    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;


    @OneToOne
    @JoinColumn(name = "`id_schedules_details`")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Schedule schedule;

    /**
     * Migrate this to Schedule because we are linking the Schedules to the Schedules Plan
     * **/




//    @OneToOne(mappedBy = "id_manager")
//    private Schedule scheduleDetails;



//    @Temporal(TemporalType.DATE)

//    @Column(name = "start_work_week")
//    private LocalDate startScheduleDate;



    @Type(type = "json")
    @Column(columnDefinition = "json", name = "`monday`")
    @JsonInclude
    private DayPlan monday;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "`tuesday`")
    @JsonInclude
    private DayPlan tuesday;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "`wednesday`")
    @JsonInclude
    private DayPlan wednesday;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "`thursday`")
    @JsonInclude
    private DayPlan thursday;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "`friday`")
    @JsonInclude
    private DayPlan friday;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "`saturday`")
    @JsonInclude
    private DayPlan saturday;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "`sunday`")
    @JsonInclude
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


    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
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

//    public Schedule getScheduleDetails() {
//        return scheduleDetails;
//    }
//
//    public void setScheduleDetails(Schedule scheduleDetails) {
//        this.scheduleDetails = scheduleDetails;
//    }

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
