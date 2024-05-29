package com.questrip.reward.api.v1;

import com.questrip.reward.api.v1.request.QuestionRequest;
import com.questrip.reward.domain.question.QuestionService;
import com.questrip.reward.support.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/api/v1/question")
    public ApiResponse<Void> registerQuestion(@Valid @RequestBody QuestionRequest request) {
        questionService.registerQuestion(request.toQuestion());

        return ApiResponse.success("문의 등록 완료");
    }
}
