package com.example.shedlockmanager.shedlock.factory;

import com.example.shedlockmanager.shedlock.dto.requestDTO.LookUpRequestDTO;
import com.example.shedlockmanager.shedlock.dto.requestPOJO.HttpRequestPOJO;

public interface RequestTransformer {
    public HttpRequestPOJO transformIntoHttpRequestPOJO(LookUpRequestDTO dto);
}
