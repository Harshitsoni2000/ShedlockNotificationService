package com.example.shedlockmanager.shedlock.service;

import com.example.shedlockmanager.shedlock.bo.Notifications;
import com.example.shedlockmanager.shedlock.dto.requestDTO.LookUpRequestDTO;
import com.example.shedlockmanager.shedlock.dto.responseDTO.LookUpResponseDTO;
import com.example.shedlockmanager.shedlock.dto.responseDTO.NotificationsResponseDTO;
import org.apache.http.HttpResponse;

public interface PushService {
    public LookUpResponseDTO processReceivedNotifications(LookUpRequestDTO requestDTO);
    public LookUpResponseDTO transformResponse(HttpResponse response, Class<NotificationsResponseDTO> notificationsResponseDTOClass);
}
