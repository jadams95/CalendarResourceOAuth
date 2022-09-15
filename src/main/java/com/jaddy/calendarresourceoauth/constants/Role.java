package com.jaddy.calendarresourceoauth.constants;


import static com.jaddy.calendarresourceoauth.constants.Authority.*;

public enum Role {
    ROLE_CUSTOMER(CUSTOMER_AUTHORITIES),
    ROLE_MANAGER(MANAGER_AUTHORITIES);
//    ROLE_ADMIN(ADMIN_AUTHORITIES);

    private String[] authorities;
    Role(String... authorities){
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
