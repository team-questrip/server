package com.questrip.reward.api.v1;

import com.questrip.reward.api.RestDocsTest;
import com.questrip.reward.domain.content.ContentService;
import com.questrip.reward.fixture.ContentFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.resourceDetails;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ContentController.class)
class ContentControllerTest extends RestDocsTest {

    @MockBean
    ContentService contentService;

    @DisplayName("페이지 조회 API")
    @Test
    void findContents() throws Exception {
        // given
        given(contentService.findAllTranslatedContent(any()))
                .willReturn(
                        List.of(ContentFixture.getTranslate())
                );

        // when
        mockMvc.perform(get("/api/v1/content")
                        .param("language", "EN")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("content-list-get",
                                resourceDetails()
                                        .tag("content")
                                        .description("컨텐츠 전체 조회 API"),
                                queryParameters(
                                        parameterWithName("language").description("번역 언어 default 영어").optional()
                                ),
                                responseFields(
                                        fieldWithPath("status").type(JsonFieldType.STRING)
                                                .description("응답 상태"),
                                        fieldWithPath("message").type(JsonFieldType.NULL)
                                                .description("메시지"),
                                        fieldWithPath("data").type(JsonFieldType.ARRAY)
                                                .description("데이터"),
                                        fieldWithPath("data[].pageId").type(JsonFieldType.STRING)
                                                .description("페이지 id"),
                                        fieldWithPath("data[].id").type(JsonFieldType.NUMBER)
                                                .description("데이터베이스 게시글 id"),
                                        fieldWithPath("data[].title").type(JsonFieldType.STRING)
                                                .description("컨텐츠 제목"),
                                        fieldWithPath("data[].tags").type(JsonFieldType.ARRAY)
                                                .description("태그 목록"),
                                        fieldWithPath("data[].category").type(JsonFieldType.ARRAY)
                                                .description("카테고리 목록"),
                                        fieldWithPath("data[].menuItems").type(JsonFieldType.ARRAY)
                                                .description("메뉴 아이템 목록"),
                                        fieldWithPath("data[].thumbnailImage").type(JsonFieldType.STRING)
                                                .description("썸네일 이미지")
                                )
                        )
                );
    }

//    @DisplayName("페이지 조회 API")
//    @Test
//    void getBlocks() throws Exception {
//        // given
//        given(contentService.getBlocks(any()))
//                .willReturn(
//                        BlockFixture.get()
//                );
//
//        // when
//        mockMvc.perform(get("/api/v1/content/{pageId}", "8860f178-e89f-488c-b68b-a0ac414e25de"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andDo(document("content-get",
//                                resourceDetails()
//                                        .tag("content")
//                                        .description("컨텐츠 상세 조회 API"),
//                                pathParameters(
//                                        parameterWithName("pageId").description("페이지 아이디")
//                                ),
//                                responseFields(
//                                        fieldWithPath("status").type(JsonFieldType.STRING)
//                                                .description("응답 상태"),
//                                        fieldWithPath("message").type(JsonFieldType.NULL)
//                                                .description("메시지"),
//                                        fieldWithPath("data").type(JsonFieldType.ARRAY)
//                                                .description("데이터"),
//                                        fieldWithPath("data[].type").type(JsonFieldType.STRING).optional()
//                                                .description("페이지 타입"),
//                                        fieldWithPath("data[].url").type(JsonFieldType.STRING).optional()
//                                                .description("이미지 url"),
//                                        fieldWithPath("data[].caption").type(JsonFieldType.STRING).optional()
//                                                .description("이미지 캡션"),
//                                        fieldWithPath("data[].text").type(JsonFieldType.STRING).optional()
//                                                .description("내용")
//                                )
//                        )
//                );
//    }

}