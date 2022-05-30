package com.example.shedlockmanager.shedlock.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource(ignoreResourceNotFound = true, value="classpath:config.properties")
public class NotificationsConfig {

    @Value("${shedlock.notifications.url}")
    private String notificationsUrl;

    @Value("${shedlock.notification.username}")
    private String notificationsUsername;

    @Value("${shedlock.notification.password}")
    private String notificationsPassword;
}
