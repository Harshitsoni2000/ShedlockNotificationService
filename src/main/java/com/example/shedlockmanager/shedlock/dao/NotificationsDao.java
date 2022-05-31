package com.example.shedlockmanager.shedlock.dao;

import com.example.shedlockmanager.shedlock.bo.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationsDao extends JpaRepository<Notifications, Integer> {

    @Query(value = "SELECT * FROM notifications WHERE breached=1", nativeQuery = true)
    public List<Notifications> getBreachedNotifications();
    @Query(value = "SELECT * FROM notifications WHERE pending=1", nativeQuery = true)
    public List<Notifications> getPendingNotifications();
    @Query(value = "SELECT * FROM notifications WHERE completed=1", nativeQuery = true)
    public List<Notifications> getCompletedNotifications();
}
