package com.example.shedlockmanager.shedlock.controller;

import com.example.shedlockmanager.shedlock.bo.Notifications;
import com.example.shedlockmanager.shedlock.dto.requestDTO.LookUpRequestDTO;
import com.example.shedlockmanager.shedlock.dto.responseDTO.LookUpResponseDTO;
import com.example.shedlockmanager.shedlock.service.PushService;
import com.example.shedlockmanager.shedlock.util.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
@Slf4j
public class PushNotifications {

    @Autowired
    private PushService pushService;

    @Autowired
    private JsonParser parser;

    @GetMapping("/webhooks")
    public Integer verifyAppIdentity(
            @RequestParam("hub.mode") String mode,
            @RequestParam("hub.challenge") Integer challenge,
            @RequestParam("hub.verify_token") String verifyToken
    ) {
        if(verifyToken.equals("*123*1#")) {
            return challenge;
        }
        return null;
    }

    @PostMapping("/webhooks")
    public void processContent(@RequestBody String data) {
        log.info("Data From Webhook :: {}", data);
        processContent(data);
    }

    @PostMapping("/sendNotifications")
    public ResponseEntity<LookUpResponseDTO> sendNotifications(@RequestBody String notification) {
        LookUpRequestDTO requestDTO = parser.generalJsonToObject(notification, Notifications.class);
        LookUpResponseDTO responseDTO = pushService.processReceivedNotifications(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
