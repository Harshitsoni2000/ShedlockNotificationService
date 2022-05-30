package com.example.shedlockmanager.shedlock.dto.requestPOJO;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.NameValuePair;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class HttpRequestPOJO {
    private String url;

    private List<NameValuePair> paramList;

    private Map<String, String> headerMap;

    private byte[] attachment;

    private JSONObject jsonBody;

    private String jsonString;

    private RequestMethod requestMethod;
}
