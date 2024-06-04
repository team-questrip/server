package com.questrip.reward.api.v1;

import com.questrip.reward.api.RestDocsTest;
import com.questrip.reward.api.v1.request.RecommendRequest;
import com.questrip.reward.domain.place.Place;
import com.questrip.reward.domain.recommend.Recommend;
import com.questrip.reward.domain.recommend.RecommendService;
import com.questrip.reward.fixture.PlaceFixture;
import com.questrip.reward.mockuser.MockUser;
import com.questrip.reward.support.response.SliceResult;
import org.apache.http.auth.AUTH;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDateTime;
import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.resourceDetails;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
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
                        .header(AUTHORIZATION, ACCESS_TOKEN)
                        .param("latitude", "37.5912474")
                        .param("longitude", "126.9184582")
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

    @DisplayName("추천 내역 추가 API")
    @MockUser
    @Test
    void save() throws Exception {
        // given
        RecommendRequest request = new RecommendRequest("test", Recommend.Status.ACCEPTED);
        given(recommendService.save(any(), any(), any())).willReturn(
                Recommend.builder()
                        .id(1L)
                        .userId(1L)
                        .place(PlaceFixture.get("test"))
                        .status(Recommend.Status.ACCEPTED)
                        .createdAt(LocalDateTime.of(2024, 6, 3, 22, 19, 0))
                        .build()
        );

        // when & then
        mockMvc.perform(post("/api/v1/recommend")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header(AUTHORIZATION, ACCESS_TOKEN)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("recommend-post",
                                resourceDetails()
                                        .tag("recommend")
                                        .description("추천 내역 추가 API"),
                                requestHeaders(
                                        headerWithName(AUTHORIZATION).description(ACCESS_TOKEN).optional()
                                ),
                                requestFields(
                                        fieldWithPath("placeId").type(JsonFieldType.STRING)
                                                .description("플레이스 아이디"),
                                        fieldWithPath("status").type(JsonFieldType.STRING)
                                                .description("[ACCEPTED, DENIED, KEPT]")
                                ),
                                responseFields(
                                        fieldWithPath("status").type(JsonFieldType.STRING)
                                                .description("응답 상태"),
                                        fieldWithPath("message").type(JsonFieldType.NULL)
                                                .description("메시지"),
                                        fieldWithPath("data").type(JsonFieldType.OBJECT)
                                                .description("데이터"),
                                        fieldWithPath("data.id").type(JsonFieldType.NUMBER)
                                                .description("추천 아이디"),
                                        fieldWithPath("data.userId").type(JsonFieldType.NUMBER)
                                                .description("유저 아이디"),
                                        fieldWithPath("data.status").type(JsonFieldType.STRING)
                                                .description("상태"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING)
                                                .description("요청 시간"),
                                        fieldWithPath("data.place").type(JsonFieldType.OBJECT)
                                                .description("장소 데이터"),
                                        fieldWithPath("data.place.id").type(JsonFieldType.STRING)
                                                .description("장소 아이디"),
                                        fieldWithPath("data.place.googlePlaceId").type(JsonFieldType.STRING)
                                                .description("구글 장소 아이디"),
                                        fieldWithPath("data.place.placeName").type(JsonFieldType.STRING)
                                                .description("장소 이름"),
                                        fieldWithPath("data.place.primaryType").type(JsonFieldType.STRING)
                                                .description("카테고리"),
                                        fieldWithPath("data.place.formattedAddress").type(JsonFieldType.STRING)
                                                .description("주소"),
                                        fieldWithPath("data.place.location").type(JsonFieldType.OBJECT)
                                                .description("위경도"),
                                        fieldWithPath("data.place.location.latitude").type(JsonFieldType.NUMBER)
                                                .description("위도"),
                                        fieldWithPath("data.place.location.longitude").type(JsonFieldType.NUMBER)
                                                .description("경도"),
                                        fieldWithPath("data.place.content").type(JsonFieldType.OBJECT)
                                                .description("장소 설명"),
                                        fieldWithPath("data.place.content.recommendationReason").type(JsonFieldType.STRING)
                                                .description("추천 이유"),
                                        fieldWithPath("data.place.content.activity").type(JsonFieldType.STRING)
                                                .description("추천 활동"),
                                        fieldWithPath("data.place.images").type(JsonFieldType.ARRAY)
                                                .description("장소 이미지"),
                                        fieldWithPath("data.place.images[].sequence").type(JsonFieldType.NUMBER)
                                                .description("이미지 순서"),
                                        fieldWithPath("data.place.images[].url").type(JsonFieldType.STRING)
                                                .description("이미지 url"),
                                        fieldWithPath("data.place.openingHours").type(JsonFieldType.ARRAY)
                                                .description("영업시간"),
                                        fieldWithPath("data.place.openNow").type(JsonFieldType.STRING)
                                                .description("현재 영업 여부 [OPEN, CLOSE, UNKNOWN]")
                                )
                        )
                );
    }

    @DisplayName("추천 저장 내역 API")
    @MockUser
    @Test
    void kept() throws Exception {
        // given

        Recommend r1 = Recommend.builder()
                .id(1L)
                .userId(1L)
                .place(PlaceFixture.get("test"))
                .status(Recommend.Status.KEPT)
                .createdAt(LocalDateTime.of(2024, 6, 3, 22, 19, 0))
                .build();
        Recommend r2 = Recommend.builder()
                .id(1L)
                .userId(1L)
                .place(PlaceFixture.get("test"))
                .status(Recommend.Status.KEPT)
                .createdAt(LocalDateTime.of(2024, 6, 3, 22, 19, 0))
                .build();
        List<Recommend> recommends = List.of(r1, r2);

        given(recommendService.getKeptRecommends(any(), anyInt(), anyInt())).willReturn(
                new SliceResult<>(recommends, 0, 10, 2, false)
        );

        // when & then
        mockMvc.perform(get("/api/v1/recommend/kept")
                        .param("page", "0")
                        .param("size", "10")
                        .header(AUTHORIZATION, ACCESS_TOKEN)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("recommend-get-kept",
                                resourceDetails()
                                        .tag("recommend")
                                        .description("추천 저장 내역 API"),
                                requestHeaders(
                                        headerWithName(AUTHORIZATION).description(ACCESS_TOKEN).optional()
                                ),
                                queryParameters(
                                        parameterWithName("page").description("요청 페이지 Default 0").optional(),
                                        parameterWithName("size").description("요청 페이지 Default 10").optional()
                                ),
                                responseFields(
                                        fieldWithPath("status").type(JsonFieldType.STRING)
                                                .description("응답 상태"),
                                        fieldWithPath("message").type(JsonFieldType.NULL)
                                                .description("메시지"),
                                        fieldWithPath("data").type(JsonFieldType.OBJECT)
                                                .description("데이터"),
                                        fieldWithPath("data.content").type(JsonFieldType.ARRAY)
                                                .description("내용"),
                                        fieldWithPath("data.page").type(JsonFieldType.NUMBER)
                                                .description("요청 페이지"),
                                        fieldWithPath("data.size").type(JsonFieldType.NUMBER)
                                                .description("요청 사이즈"),
                                        fieldWithPath("data.numberOfElements").type(JsonFieldType.NUMBER)
                                                .description("실제 반환 데이터 수"),
                                        fieldWithPath("data.hasNext").type(JsonFieldType.BOOLEAN)
                                                .description("다음 페이지 여부"),
                                        fieldWithPath("data.content[].id").type(JsonFieldType.NUMBER)
                                                .description("추천 아이디"),
                                        fieldWithPath("data.content[].userId").type(JsonFieldType.NUMBER)
                                                .description("유저 아이디"),
                                        fieldWithPath("data.content[].status").type(JsonFieldType.STRING)
                                                .description("상태"),
                                        fieldWithPath("data.content[].createdAt").type(JsonFieldType.STRING)
                                                .description("요청 시간"),
                                        fieldWithPath("data.content[].place").type(JsonFieldType.OBJECT)
                                                .description("장소 데이터"),
                                        fieldWithPath("data.content[].place.id").type(JsonFieldType.STRING)
                                                .description("장소 아이디"),
                                        fieldWithPath("data.content[].place.googlePlaceId").type(JsonFieldType.STRING)
                                                .description("구글 장소 아이디"),
                                        fieldWithPath("data.content[].place.placeName").type(JsonFieldType.STRING)
                                                .description("장소 이름"),
                                        fieldWithPath("data.content[].place.primaryType").type(JsonFieldType.STRING)
                                                .description("카테고리"),
                                        fieldWithPath("data.content[].place.formattedAddress").type(JsonFieldType.STRING)
                                                .description("주소"),
                                        fieldWithPath("data.content[].place.location").type(JsonFieldType.OBJECT)
                                                .description("위경도"),
                                        fieldWithPath("data.content[].place.location.latitude").type(JsonFieldType.NUMBER)
                                                .description("위도"),
                                        fieldWithPath("data.content[].place.location.longitude").type(JsonFieldType.NUMBER)
                                                .description("경도"),
                                        fieldWithPath("data.content[].place.content").type(JsonFieldType.OBJECT)
                                                .description("장소 설명"),
                                        fieldWithPath("data.content[].place.content.recommendationReason").type(JsonFieldType.STRING)
                                                .description("추천 이유"),
                                        fieldWithPath("data.content[].place.content.activity").type(JsonFieldType.STRING)
                                                .description("추천 활동"),
                                        fieldWithPath("data.content[].place.images").type(JsonFieldType.ARRAY)
                                                .description("장소 이미지"),
                                        fieldWithPath("data.content[].place.images[].sequence").type(JsonFieldType.NUMBER)
                                                .description("이미지 순서"),
                                        fieldWithPath("data.content[].place.images[].url").type(JsonFieldType.STRING)
                                                .description("이미지 url"),
                                        fieldWithPath("data.content[].place.openingHours").type(JsonFieldType.ARRAY)
                                                .description("영업시간"),
                                        fieldWithPath("data.content[].place.openNow").type(JsonFieldType.STRING)
                                                .description("현재 영업 여부 [OPEN, CLOSE, UNKNOWN]")
                                )
                        )
                );
    }
}