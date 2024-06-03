package com.questrip.reward.api.v1;

import com.questrip.reward.api.RestDocsTest;
import com.questrip.reward.domain.place.Place;
import com.questrip.reward.domain.recommend.RecommendService;
import com.questrip.reward.fixture.PlaceFixture;
import com.questrip.reward.mockuser.MockUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.resourceDetails;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecommendController.class)
class RecommendControllerTest extends RestDocsTest {

    @MockBean
    RecommendService recommendService;

    @DisplayName("추천 장소 목록을 조회한다.")
    @MockUser
    @Test
    void getRecommends() throws Exception {
        // given
        List<Place> places = List.of(PlaceFixture.get("test"));
        given(recommendService.getRecommendPlaces(any(), any()))
                .willReturn(places);

        // when
        mockMvc.perform(get("/api/v1/recommend")
                        .param("latitude", "37.5912474")
                        .param("longitude", "126.9184582")
                        .header(AUTHORIZATION, ACCESS_TOKEN)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("recommend-get",
                        resourceDetails()
                                .tag("recommend")
                                .description("추천 장소 API"),
                        requestHeaders(
                                headerWithName(AUTHORIZATION).description(ACCESS_TOKEN)
                        ),
                        queryParameters(
                                parameterWithName("latitude").description("유저 위도"),
                                parameterWithName("longitude").description("유저 경도")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("데이터"),
                                fieldWithPath("data.places").type(JsonFieldType.ARRAY)
                                        .description("장소 목록"),
                                fieldWithPath("data.places[].id").type(JsonFieldType.STRING)
                                        .description("장소 아이디"),
                                fieldWithPath("data.places[].googlePlaceId").type(JsonFieldType.STRING)
                                        .description("구글 장소 아이디"),
                                fieldWithPath("data.places[].placeName").type(JsonFieldType.STRING)
                                        .description("장소 이름"),
                                fieldWithPath("data.places[].primaryType").type(JsonFieldType.STRING)
                                        .description("카테고리"),
                                fieldWithPath("data.places[].formattedAddress").type(JsonFieldType.STRING)
                                        .description("주소"),
                                fieldWithPath("data.places[].location").type(JsonFieldType.OBJECT)
                                        .description("위경도"),
                                fieldWithPath("data.places[].location.latitude").type(JsonFieldType.NUMBER)
                                        .description("위도"),
                                fieldWithPath("data.places[].location.longitude").type(JsonFieldType.NUMBER)
                                        .description("경도"),
                                fieldWithPath("data.places[].content").type(JsonFieldType.OBJECT)
                                        .description("장소 설명"),
                                fieldWithPath("data.places[].content.recommendationReason").type(JsonFieldType.STRING)
                                        .description("추천 이유"),
                                fieldWithPath("data.places[].content.activity").type(JsonFieldType.STRING)
                                        .description("추천 활동"),
                                fieldWithPath("data.places[].images").type(JsonFieldType.ARRAY)
                                        .description("장소 이미지"),
                                fieldWithPath("data.places[].images[].sequence").type(JsonFieldType.NUMBER)
                                        .description("이미지 순서"),
                                fieldWithPath("data.places[].images[].url").type(JsonFieldType.STRING)
                                        .description("이미지 url"),
                                fieldWithPath("data.places[].openingHours").type(JsonFieldType.ARRAY)
                                        .description("영업시간"),
                                fieldWithPath("data.places[].openNow").type(JsonFieldType.STRING)
                                        .description("현재 영업 여부 [OPEN, CLOSE, UNKNOWN]")
                        ))

                );
    }
}