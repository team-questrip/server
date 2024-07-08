package com.questrip.reward.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.questrip.reward.client.request.DeeplTranslateRequest;
import com.questrip.reward.client.response.DeeplTranslateResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DeeplTranslateClientTest {

    @Autowired
    DeeplTranslateClient deeplTranslateClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("")
    @Test
    void getTranslate() throws JsonProcessingException {
        // given
        DeeplTranslateRequest request = new DeeplTranslateRequest(List.of("Among the many boyangshik dishes, Samgyetang reigns supreme, especially during Boknal. A whole young chicken is generously stuffed with nourishing ingredients like ginseng, jujube dates, garlic, and glutinous rice, then simmered to perfection. Samgyetang is a true restorative, offering a rich and savory broth, along with ample protein and nutrients to revitalize tired bodies."), "en", "ko");

        objectMapper.writeValueAsString(request);
        System.out.println("request = " + request);
        // when
        DeeplTranslateResponse response = deeplTranslateClient.getTranslate(request);

        // then
        System.out.println(response);
    }
}