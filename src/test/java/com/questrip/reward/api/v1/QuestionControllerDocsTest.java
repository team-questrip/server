package com.questrip.reward.api.v1;

import com.questrip.reward.api.RestDocsTest;
import com.questrip.reward.api.v1.request.QuestionRequest;
import com.questrip.reward.domain.question.Question;
import com.questrip.reward.domain.question.QuestionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.resourceDetails;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class QuestionControllerDocsTest extends RestDocsTest {

    private final QuestionService questionService = mock(QuestionService.class);

    @Override
    protected Object initializeController() {
        return new QuestionController(questionService);
    }

    @DisplayName("문의 등록 API")
    @Test
    void registerQuestion() throws Exception {
        // given
        QuestionRequest request = new QuestionRequest("서비스 문의드립니다.", "newjeans@queen.kpop", Question.Category.SERVICE_QUESTION);

        // when & then
        mockMvc.perform(post("/api/v1/question")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("question-post",
                        resourceDetails()
                                .tag("question")
                                .description("문의 등록 API"),
                        requestFields(
                                fieldWithPath("content").type(JsonFieldType.STRING)
                                        .description("내용"),
                                fieldWithPath("userEmail").type(JsonFieldType.STRING)
                                        .description("유저 메일"),
                                fieldWithPath("category").type(JsonFieldType.STRING)
                                        .description("문의 유형")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.NULL)
                                        .description("데이터")
                        ))
                );
    }
}