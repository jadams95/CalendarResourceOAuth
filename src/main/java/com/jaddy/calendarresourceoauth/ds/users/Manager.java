package com.jaddy.calendarresourceoauth.ds.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jaddy.calendarresourceoauth.constants.Role;
import com.jaddy.calendarresourceoauth.ds.Appointment;
import com.jaddy.calendarresourceoauth.ds.Schedule;
import com.jaddy.calendarresourceoauth.ds.SchedulePlan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Entity
@Table(name = "managers")
@PrimaryKeyJoinColumn(name = "id_manager")
public class Manager implements UserDetails {
    @Id
    @Column(name = "id_manager", nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;


    @Column(name = "authorities")
    private String[] authorities;


    @OneToMany(mappedBy = "manager")
    @JsonBackReference
    private List<Appointment> appointments;

    @ManyToMany
    @JoinTable(name = "schedule_managers", joinColumns = @JoinColumn(name = "id_manager"), inverseJoinColumns = @JoinColumn(name = "id_schedules_details"))
    private List<Schedule> schedules;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Manager() {
    }

    public Manager(Long id, String username, String password, String role, String[] authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
    }


    public Manager(Long id, String username, String password, String role, String[] authorities, List<Schedule> schedules) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
        this.schedules = schedules;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public String getRole() {
        return role;
    }

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return stream(Role.ROLE_MANAGER.getAuthorities()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
