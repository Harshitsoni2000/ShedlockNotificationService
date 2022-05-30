package com.example.shedlockmanager.shedlock.service;

import com.example.shedlockmanager.shedlock.bo.Notifications;
import com.example.shedlockmanager.shedlock.config.NotificationsConfig;
import com.example.shedlockmanager.shedlock.dao.NotificationsDao;
import com.example.shedlockmanager.shedlock.dto.requestDTO.LookUpRequestDTO;
import com.example.shedlockmanager.shedlock.dto.requestDTO.NotificationsRequestDTO;
import com.example.shedlockmanager.shedlock.dto.requestPOJO.HttpRequestPOJO;
import com.example.shedlockmanager.shedlock.dto.responseDTO.LookUpResponseDTO;
import com.example.shedlockmanager.shedlock.dto.responseDTO.NotificationsResponseDTO;
import com.example.shedlockmanager.shedlock.factory.RequestTransformer;
import com.example.shedlockmanager.shedlock.util.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PushServiceImpl implements PushService {

    @Autowired
    private NotificationsDao notificationsDao;

    @Autowired
    private RequestTransformer requestTransformer;

    @Autowired
    private JsonParser parser;

    @Autowired
    private NotificationsConfig config;

    @Override
    public LookUpResponseDTO processReceivedNotifications(LookUpRequestDTO requestDTO) {

        // Populate Notification data in DB
        Notifications notifications = parser.generalObjectToAnotherObject(requestDTO, Notifications.class);
        notificationsDao.save(notifications);

        // Send a Message to User
        LookUpResponseDTO responseDTO = sendNotificationsToUser(notifications);

        return responseDTO;
    }

    private LookUpResponseDTO sendNotificationsToUser(Notifications notifications) {
        NotificationsRequestDTO requestDTO = parser.generalObjectToAnotherObject(notifications, NotificationsRequestDTO.class);

        HttpRequestPOJO transformedRequest = requestTransformer.transformIntoHttpRequestPOJO(requestDTO);

        HttpResponse response = null;
        HttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(transformedRequest.getUrl());
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");

        String auth = config.getNotificationsUsername() + ":" + config.getNotificationsPassword();
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String(encodedAuth);
        httpPost.setHeader("Authorization", authHeader);

        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(new BasicNameValuePair("Body", notifications.toString()));
        nameValuePairList.add(new BasicNameValuePair("From", "+19498674623"));
        nameValuePairList.add(new BasicNameValuePair("To", "+918005769110"));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            response = client.execute(httpPost);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return this.transformResponse(response, NotificationsResponseDTO.class);
    }

    public LookUpResponseDTO transformResponse(HttpResponse response, Class<NotificationsResponseDTO> notificationsResponseDTOClass) {

        String retSrc=null;

        try {
            retSrc = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        if(!HttpStatus.valueOf(response.getStatusLine().getStatusCode()).is2xxSuccessful()) {
            log.info("Unsuccessful response from remote with status code :: {}, response :: {}", response.getStatusLine().getStatusCode(), retSrc);
            System.exit(-1);
        }

        log.info("Response ::=:: {}",response);

        LookUpResponseDTO responseDTO = parser.generalJsonToObject(retSrc, NotificationsResponseDTO.class);

        return responseDTO;
    }
}
