package com.example.shedlockmanager.shedlock.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class JsonParser {

    public static String objectToJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;
        try {
            jsonInString = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            log.error("Unable to convert Object :: {} to class", obj);
        }
        return jsonInString;
    }

    public static <T> T generalJsonToObject(String json, Class<?> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        T object = null;
        try {
            object = (T)mapper.readValue(json, clazz);
        } catch(IOException e) {
            log.error("Unable to convert Json to Class :: "+clazz.getName());
            System.exit(-1);
        }
        return object;
    }

    public static <T> T generalObjectToAnotherObject(Object obj, Class<?> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        T convertedObj = null;
        try {
            convertedObj = (T)mapper.convertValue(obj, clazz);
        } catch(Exception e) {
            log.error("Unable to convert Json to Class :: "+clazz.getName());
            System.exit(-1);
        }
        return convertedObj;
    }
}
