package com.jaddy.calendarresourceoauth.model.dtos;

import com.jaddy.calendarresourceoauth.ds.users.Manager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

//public class ManagerPrincipal implements UserDetails {
//    private Manager manager;
//    public ManagerPrincipal(Manager manager){
//        this.manager = manager;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return manager.getAuthorities();
//    }
//
//    @Override
//    public String getPassword() {
//        return manager.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return manager.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
