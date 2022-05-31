package com.example.shedlockmanager.shedlock.scheduler;

import com.example.shedlockmanager.shedlock.bo.Notifications;
import com.example.shedlockmanager.shedlock.dao.NotificationsDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class Scheduler {

    @Autowired
    private NotificationsDao notificationsDao;

    @Scheduled(fixedRate = 5000)
    public void cronJobSch() {
        List<Notifications> breachedNotifications = notificationsDao.getBreachedNotifications();
        List<Notifications> pendingNotifications = notificationsDao.getPendingNotifications();
        List<Notifications> completedNotifications = notificationsDao.getCompletedNotifications();

        log.info("Breached Notifications :: "+breachedNotifications);
        log.info("Pending Notifications :: "+pendingNotifications);
        log.info("Completed Notifications :: "+completedNotifications);
    }
}
