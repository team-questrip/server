package com.questrip.reward.api.v1;

import com.questrip.reward.api.RestDocsTest;
import com.questrip.reward.domain.content.Content;
import com.questrip.reward.domain.content.ContentService;
import com.questrip.reward.fixture.ContentFixture;
import com.questrip.reward.support.response.SliceResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.ArrayList;
import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.resourceDetails;
import static org.mockito.ArgumentMatchers.anyInt;
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

    @DisplayName("컨텐츠 조회 API")
    @Test
    void findContent() throws Exception {
        // given
        given(contentService.findContent(any()))
                .willReturn(ContentFixture.get("contentId"));

        // when
        mockMvc.perform(get("/api/v1/content/{contentId}", "contentId"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("content-get",
                                resourceDetails()
                                        .tag("content")
                                        .description("컨텐츠 조회 API"),
                                pathParameters(
                                        parameterWithName("contentId").description("컨텐츠 아이디")
                                ),
                                responseFields(
                                        fieldWithPath("status").type(JsonFieldType.STRING)
                                                .description("응답 상태"),
                                        fieldWithPath("message").type(JsonFieldType.NULL)
                                                .description("메시지"),
                                        fieldWithPath("data").type(JsonFieldType.OBJECT)
                                                .description("데이터"),
                                        fieldWithPath("data.id").type(JsonFieldType.STRING)
                                                .description("컨텐츠 id"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING)
                                                .description("컨텐츠 제목"),
                                        fieldWithPath("data.category").type(JsonFieldType.STRING)
                                                .description("컨텐츠 카테고리"),
                                        fieldWithPath("data.tags").type(JsonFieldType.ARRAY)
                                                .description("컨텐츠 태그"),
                                        fieldWithPath("data.images").type(JsonFieldType.STRING)
                                                .description("컨텐츠 이미지"),
                                        fieldWithPath("data.sections[]").type(JsonFieldType.ARRAY)
                                                .description("컨텐츠 섹션"),
                                        fieldWithPath("data.sections[].title").type(JsonFieldType.STRING)
                                                .description("섹션 제목"),
                                        fieldWithPath("data.sections[].content").type(JsonFieldType.STRING)
                                                .description("섹션 내용"),
                                        fieldWithPath("data.sections[].image").type(JsonFieldType.STRING)
                                                .description("섹션 이미지"),
                                        fieldWithPath("data.sections[].bulletedList[]").type(JsonFieldType.ARRAY)
                                                .description("섹션 bullet list"),
                                        fieldWithPath("data.sections[].bulletedList[].title").type(JsonFieldType.STRING)
                                                .description("bullet list 제목"),
                                        fieldWithPath("data.sections[].bulletedList[].items[]").type(JsonFieldType.ARRAY)
                                                .description("섹션 bullet list 제목 내용")
                                )
                        )
                );


        // then
    }

    @DisplayName("컨텐츠 리스트 조회 API")
    @Test
    void findContents() throws Exception {
        // given
        List<Content> contents = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            contents.add(ContentFixture.get("contentId" + i));
        }
        given(contentService.findContentsBy(anyInt(), anyInt()))
                .willReturn(new SliceResult<>(contents, 0, 5, 5, false));

        // when
        mockMvc.perform(get("/api/v1/content")
                        .param("page", "0")
                        .param("size", "5")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("content-list-get",
                                resourceDetails()
                                        .tag("content")
                                        .description("컨텐츠 리스트 조회 API"),
                                queryParameters(
                                        parameterWithName("page").description("요청 페이지 (default 0)"),
                                        parameterWithName("size").description("요청 사이즈 (default 5)")
                                ),
                                responseFields(
                                        fieldWithPath("status").type(JsonFieldType.STRING)
                                                .description("응답 상태"),
                                        fieldWithPath("message").type(JsonFieldType.NULL)
                                                .description("메시지"),
                                        fieldWithPath("data").type(JsonFieldType.OBJECT)
                                                .description("데이터"),
                                        fieldWithPath("data.content[].id").type(JsonFieldType.STRING)
                                                .description("컨텐츠 id"),
                                        fieldWithPath("data.content[].title").type(JsonFieldType.STRING)
                                                .description("컨텐츠 제목"),
                                        fieldWithPath("data.content[].category").type(JsonFieldType.STRING)
                                                .description("컨텐츠 카테고리"),
                                        fieldWithPath("data.content[].tags").type(JsonFieldType.ARRAY)
                                                .description("컨텐츠 태그"),
                                        fieldWithPath("data.content[].images").type(JsonFieldType.STRING)
                                                .description("컨텐츠 이미지"),
                                        fieldWithPath("data.content[].sections[]").type(JsonFieldType.ARRAY)
                                                .description("컨텐츠 섹션"),
                                        fieldWithPath("data.content[].sections[].title").type(JsonFieldType.STRING)
                                                .description("섹션 제목"),
                                        fieldWithPath("data.content[].sections[].content").type(JsonFieldType.STRING)
                                                .description("섹션 내용"),
                                        fieldWithPath("data.content[].sections[].image").type(JsonFieldType.STRING)
                                                .description("섹션 이미지"),
                                        fieldWithPath("data.content[].sections[].bulletedList[]").type(JsonFieldType.ARRAY)
                                                .description("섹션 bullet list"),
                                        fieldWithPath("data.content[].sections[].bulletedList[].title").type(JsonFieldType.STRING)
                                                .description("bullet list 제목"),
                                        fieldWithPath("data.content[].sections[].bulletedList[].items[]").type(JsonFieldType.ARRAY)
                                                .description("섹션 bullet list 제목 내용"),
                                        fieldWithPath("data.page").type(JsonFieldType.NUMBER)
                                                .description("요청 페이지"),
                                        fieldWithPath("data.size").type(JsonFieldType.NUMBER)
                                                .description("요청 사이즈"),
                                        fieldWithPath("data.numberOfElements").type(JsonFieldType.NUMBER)
                                                .description("실제 데이터 개수"),
                                        fieldWithPath("data.hasNext").type(JsonFieldType.BOOLEAN)
                                                .description("다음 페이지 존재 여부")
                                )
                        )
                );


        // then
    }
}