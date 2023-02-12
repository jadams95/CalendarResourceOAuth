package com.jaddy.calendarresourceoauth.ds.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jaddy.calendarresourceoauth.constants.Role;
import com.jaddy.calendarresourceoauth.ds.Appointment;
import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.ds.SchedulePlan;
import com.nimbusds.jose.shaded.json.annotate.JsonIgnore;
import org.hibernate.annotations.Type;
//import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//import javax.persistence.Id;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "`managers`")
@PrimaryKeyJoinColumn(name = "`uid_manager`")
public class Manager {

    @Id
    Long id;

    @Column(name = "username")
    private String username;



//    @Id
//    @Column(name = "`id`", nullable = false)
//    private Long id;
//
//    @Column(name = "`username`")
//    private String username;
//
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @Column(name = "`password`")
//    private String password;
//
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @Column(name = "`role`")
//    private String role;
//
//
//
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @Column(columnDefinition = "text[]", name = "`authorities`")
//    @Type(type = "com.jaddy.calendarresourceoauth.utils.PostgreSqlStringArrayType")
//    private String[] authorities;


    @OneToMany(mappedBy = "manager")
    @JsonBackReference
    private List<Appointment> appointments;

    @ManyToMany
    @JoinTable(name = "`schedule_managers_table`", joinColumns = @JoinColumn(name = "`uid_manager`"), inverseJoinColumns = @JoinColumn(name = "`manager_schedule`"))
    private List<Schedule> schedules = new ArrayList<>();

    //
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//
//    public void setAuthorities(String[] authorities) {
//        this.authorities = authorities;
//    }
//
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Manager() {
    }

    public Manager(Long id, String username) {
        this.id = id;
        this.username = username;
    }
    //    public Manager(Long id, String username, String password, String role, String[] authorities) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.role = role;
//        this.authorities = authorities;
//    }


//    public Manager(Long id, String username, String password, String role, String[] authorities, List<Schedule> schedules) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.role = role;
//        this.authorities = authorities;
//        this.schedules = schedules;
//    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
//
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addSchedule(Schedule schedule){
        this.schedules.add(schedule);
    }
    public List<Schedule> getSchedules() {
        return schedules;
    }

//    public String getRole() {
//        return role;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return manager.getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointments, schedules);
    }

}
