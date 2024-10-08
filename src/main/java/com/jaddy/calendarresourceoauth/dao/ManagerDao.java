package com.jaddy.calendarresourceoauth.dao;

import com.jaddy.calendarresourceoauth.ds.users.Manager;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerDao extends JpaRepository<Manager, Long> {
    Manager findManagerBySchedules(Long scheduleId);

    Manager findByUsername(String userName);
}
