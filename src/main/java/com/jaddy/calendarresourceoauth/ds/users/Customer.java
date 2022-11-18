package com.jaddy.calendarresourceoauth.ds.users;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.jaddy.calendarresourceoauth.constants.Role;
import com.jaddy.calendarresourceoauth.ds.Appointment;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Entity
@Table(name = "`customers`", schema = "public")
@PrimaryKeyJoinColumn(name = "id_customer")
public class Customer implements UserDetails {
    @Id
    @Column(name = "`id`", nullable = false)
    private Long id;

    @Column(name = "`username`")
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(columnDefinition = "text[]", name = "`authorities`")
    @Type(type = "com.jaddy.calendarresourceoauth.utils.PostgreSqlStringArrayType")
    private String[] authorities;


    @OneToMany(mappedBy = "customer")
    private List<Appointment> appointments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Customer(){
    }


    public Customer(Long id, String username, String password, String role, String[] authorities){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
    }


    public Customer(Long id, String username, String password, String role, String[] authorities, List<Appointment> appointments){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
        this.appointments = appointments;
    }



    //    @OneToMany
//    private List<Appointment> appointmentList;

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return stream(Role.ROLE_CUSTOMER.getAuthorities()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
