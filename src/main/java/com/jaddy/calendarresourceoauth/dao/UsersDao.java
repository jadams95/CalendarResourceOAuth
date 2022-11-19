package com.jaddy.calendarresourceoauth.dao;

import com.jaddy.calendarresourceoauth.ds.users.Manager;
import com.jaddy.calendarresourceoauth.ds.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersDao extends JpaRepository<Users, Long> {
    Users findByUsername(String userName);
}
