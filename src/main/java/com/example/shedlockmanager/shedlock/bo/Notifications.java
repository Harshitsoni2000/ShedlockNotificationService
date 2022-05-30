package com.example.shedlockmanager.shedlock.bo;

import com.example.shedlockmanager.shedlock.dto.requestDTO.LookUpRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name="notifications")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Notifications implements LookUpRequestDTO {
    @Id
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
