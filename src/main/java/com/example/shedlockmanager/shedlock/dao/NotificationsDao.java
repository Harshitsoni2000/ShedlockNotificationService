package com.example.shedlockmanager.shedlock.dao;

import com.example.shedlockmanager.shedlock.bo.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationsDao extends JpaRepository<Notifications, Integer> {
}
