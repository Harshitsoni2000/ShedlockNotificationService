package com.example.shedlockmanager.shedlock.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationsResponseDTO implements LookUpResponseDTO {
    private String sid;
    private String date_created;
    private String date_updated;
    private String to;
    private String from;
    private String body;
}
