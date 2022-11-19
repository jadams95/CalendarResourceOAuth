package com.jaddy.calendarresourceoauth.model.dtos;

import com.jaddy.calendarresourceoauth.ds.users.Customer;
import com.jaddy.calendarresourceoauth.ds.users.Manager;

public class CustomerDTO extends Customer {

    private Long id;
    private String username;

    public CustomerDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}