package ru.chmelev.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
@AllArgsConstructor
public class ResourceUtils {

    private ObjectMapper objectMapper;

    public String getJsonFromResources(String resourcesPath, Class<?> targetClass) {
        InputStream resourceAsStream = getInputStreamFromResources(resourcesPath);

        try {
            Object reportInstance = objectMapper.readValue(resourceAsStream, targetClass);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return objectMapper.writeValueAsString(reportInstance);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public <T> T getObjectFromResources(String resourcesPath, Class<T> targetClass) {
        InputStream resourceAsStream = getInputStreamFromResources(resourcesPath);
        try {
            return objectMapper.readValue(resourceAsStream, targetClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getObjectFromResources(String resourcesPath, TypeReference<T> targetClass) {
        InputStream resourceAsStream = getInputStreamFromResources(resourcesPath);
        try {
            return objectMapper.readValue(resourceAsStream, targetClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public InputStream getInputStreamFromResources(String resourcesPath) {
        return this.getClass().getClassLoader().getResourceAsStream(resourcesPath);
    }

    public String getWrappedJsonFileContent(String resourcesPath, Class<?> targetClass){
        String body = getJsonFromResources(resourcesPath, targetClass);
        return "{\n" +
                "  \"status\": true,\n" +
                "  \"response\": " +
                body + ",\n" +
                "  \"errors\": []\n" +
                "}";
    }
}
