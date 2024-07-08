package com.questrip.reward.client;

import com.questrip.reward.client.request.DeeplTargetRequest;
import com.questrip.reward.client.request.DeeplTranslateRequest;
import com.questrip.reward.client.response.DeeplTranslateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "deeplApi", url = "https://api-free.deepl.com/v2/translate", configuration = FeignConfig.class)
public interface DeeplTranslateClient {

    @PostMapping
    DeeplTranslateResponse getTranslate(@RequestBody DeeplTranslateRequest request);

    @PostMapping
    DeeplTranslateResponse getTranslate(@RequestBody DeeplTargetRequest request);
}
