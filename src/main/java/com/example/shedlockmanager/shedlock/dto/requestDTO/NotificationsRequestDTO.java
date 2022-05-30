package com.example.shedlockmanager.shedlock.dto.requestDTO;

import com.example.shedlockmanager.shedlock.dto.requestDTO.LookUpRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationsRequestDTO implements LookUpRequestDTO {
    private Integer id;
    private String brand_id;
    private String brand_user_id;
    private String ticket_id;
    private String label;
    private String reminder_time;
    private Byte breached;
    private Byte pending;
    private Byte completed;
    private Byte status;
}
