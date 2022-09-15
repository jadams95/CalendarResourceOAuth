package com.jaddy.calendarresourceoauth.dao;

import com.jaddy.calendarresourceoauth.ds.users.Manager;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ManagerDao extends JpaRepository<Manager, Long> {
//    @EntityGraph(attributePaths = {"schedulePlan.manager"})
//    @Query("select m from Manager m where m.username=:username")
    Manager findByUsername(String userName);
}
