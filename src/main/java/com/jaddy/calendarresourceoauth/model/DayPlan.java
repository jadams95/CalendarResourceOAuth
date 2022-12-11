package com.jaddy.calendarresourceoauth.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DayPlan {

    private LocalDate eventPlannerDate;
    private TimePeriod workingHours;
    private List<TimePeriod> breaks;





    public DayPlan() {
        breaks = new ArrayList();
    }

    public DayPlan(TimePeriod workingHours) {
        this.workingHours = workingHours;
        this.breaks = new ArrayList();
    }

    public DayPlan(LocalDate eventPlannerDate,TimePeriod workingHours) {
        this.eventPlannerDate = eventPlannerDate;
        this.workingHours = workingHours;
        this.breaks = new ArrayList();
    }


    public List<TimePeriod> getTimePeroidsWithBreaksExcluded() {
        ArrayList<TimePeriod> timePeroidsWithBreaksExcluded = new ArrayList<>();
        timePeroidsWithBreaksExcluded.add(getWorkingHours());
        List<TimePeriod> breaks = getBreaks();

        if (!breaks.isEmpty()) {
            ArrayList<TimePeriod> toAdd = new ArrayList();
            for (TimePeriod break1 : breaks) {
                if (break1.getStart().isBefore(workingHours.getStart())) {
                    break1.setStart(workingHours.getStart());
                }
                if (break1.getEnd().isAfter(workingHours.getEnd())) {
                    break1.setEnd(workingHours.getEnd());
                }
                for (TimePeriod peroid : timePeroidsWithBreaksExcluded) {
                    if (break1.getStart().equals(peroid.getStart()) && break1.getEnd().isAfter(peroid.getStart()) && break1.getEnd().isBefore(peroid.getEnd())) {
                        peroid.setStart(break1.getEnd());
                    }
                    if (break1.getStart().isAfter(peroid.getStart()) && break1.getStart().isBefore(peroid.getEnd()) && break1.getEnd().equals(peroid.getEnd())) {
                        peroid.setEnd(break1.getStart());
                    }
                    if (break1.getStart().isAfter(peroid.getStart()) && break1.getEnd().isBefore(peroid.getEnd())) {
                        toAdd.add(new TimePeriod(peroid.getStart(), break1.getStart()));
                        peroid.setStart(break1.getEnd());
                    }
                }
            }
            timePeroidsWithBreaksExcluded.addAll(toAdd);
            Collections.sort(timePeroidsWithBreaksExcluded);
        }


        return timePeroidsWithBreaksExcluded;
    }

    public LocalDate getEventPlannerDate() {
        return eventPlannerDate;
    }

    public void setEventPlannerDate(LocalDate eventPlannerDate) {
        this.eventPlannerDate = eventPlannerDate;
    }

    public TimePeriod getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(TimePeriod workingHours) {
        this.workingHours = workingHours;
    }

    public List<TimePeriod> getBreaks() {
        return breaks;
    }

    public void setBreaks(List<TimePeriod> breaks) {
        this.breaks = breaks;
    }

    public void removeBreak(TimePeriod breakToRemove) {
        breaks.remove(breakToRemove);
    }

    public void addBreak(TimePeriod breakToAdd) {
        breaks.add(breakToAdd);
    }



}
