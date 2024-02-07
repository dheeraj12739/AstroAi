package com.astro.ai.astroai.annotationProcessing;

import com.astro.ai.astroai.annotation.JsonElement;
import com.astro.ai.astroai.annotation.JsonSerializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ObjectToJsonSerializable {

    public String convertToJson(Object object) throws Exception{

        checkIfMarkedAsSerializable(object);
        initializeObject(object);
        checkFieldForMarking(object);
    }

    private void initializeObject(Object object) throws InvocationTargetException, IllegalAccessException {
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {

            method.setAccessible(true);
            method.invoke(object);
        }
    }

    private void checkFieldForMarking(Object object) throws Exception{
        Class<?> clazz = object.getClass();

        Map<String, String> jsonElementsMap = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {

            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonElement.class)) {
                jsonElementsMap.put(getkey(field), (String) field.get(object));
            }
        }
        jsonElementsMap.entrySet().stream().map(entry-> )
    }

    private String getkey(Field field) {

        JsonElement jsonElement = field.getAnnotation(JsonElement.class);
        String key = jsonElement.key();
        if (key.isEmpty()) {
            field.getName();
        }else {
            return key;
        }

        return null;
    }

    private void checkIfMarkedAsSerializable(Object object) {

        if (Objects.isNull(object)) {

        }
        if(!object.getClass().isAnnotationPresent(JsonSerializable.class)) {

        }
    }
}
