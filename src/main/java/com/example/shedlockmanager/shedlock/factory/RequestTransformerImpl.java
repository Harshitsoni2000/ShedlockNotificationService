package com.example.shedlockmanager.shedlock.factory;

import com.example.shedlockmanager.shedlock.config.NotificationsConfig;
import com.example.shedlockmanager.shedlock.dto.requestDTO.LookUpRequestDTO;
import com.example.shedlockmanager.shedlock.dto.requestDTO.NotificationsRequestDTO;
import com.example.shedlockmanager.shedlock.dto.requestPOJO.HttpRequestPOJO;
import com.example.shedlockmanager.shedlock.util.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@Slf4j
public class RequestTransformerImpl implements RequestTransformer {

    @Autowired
    private NotificationsConfig notificationsConfig;

    public HttpRequestPOJO transformIntoHttpRequestPOJO(LookUpRequestDTO dto) {
        NotificationsRequestDTO requestDTO = (NotificationsRequestDTO)dto;

        HttpRequestPOJO httpRequestPOJO = new HttpRequestPOJO();
        httpRequestPOJO.setUrl(notificationsConfig.getNotificationsUrl());
        httpRequestPOJO.setRequestMethod(RequestMethod.POST);
        JSONObject jsonObject = null;
        try {
             jsonObject = new JSONObject(JsonParser.objectToJson(requestDTO));
        } catch (JSONException e) {
            log.error(e.getMessage());
        }
        httpRequestPOJO.setJsonBody(jsonObject);

        return httpRequestPOJO;
    }
}
