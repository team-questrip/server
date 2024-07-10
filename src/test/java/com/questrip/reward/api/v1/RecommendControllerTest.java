package com.questrip.reward.api.v1;

import com.questrip.reward.api.RestDocsTest;
import com.questrip.reward.api.v1.request.RecommendRequest;
import com.questrip.reward.domain.place.Place;
import com.questrip.reward.domain.recommend.Recommend;
import com.questrip.reward.domain.recommend.RecommendService;
import com.questrip.reward.fixture.PlaceFixture;
import com.questrip.reward.mockuser.MockUser;
import com.questrip.reward.support.response.SliceResult;
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
import static org.springframework.restdocs.request.RequestDocumentation.*;
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
        given(recommendService.getRecommendPlaces(any(), any(), anyInt(), anyInt(), any()))
                .willReturn(new SliceResult<>(places, 0, 10, 1, false));

        // when
        mockMvc.perform(get("/api/v1/recommend")
                        .header(AUTHORIZATION, ACCESS_TOKEN)
                        .param("latitude", "37.5912474")
                        .param("longitude", "126.9184582")
                        .param("page", "0")
                        .param("size", "10")
                        .param("language", "EN")
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
                                parameterWithName("longitude").description("유저 경도"),
                                parameterWithName("page").description("요청 페이지 (default 0)").optional(),
                                parameterWithName("size").description("요청 사이즈 (default 10)").optional(),
                                parameterWithName("language").description("언어").optional()
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.ARRAY)
                                        .description("데이터"),
                                fieldWithPath("data[].id").type(JsonFieldType.STRING)
                                        .description("장소 아이디"),
                                fieldWithPath("data[].googlePlaceId").type(JsonFieldType.STRING)
                                        .description("구글 장소 아이디"),
                                fieldWithPath("data[].placeName").type(JsonFieldType.STRING)
                                        .description("장소 이름"),
                                fieldWithPath("data[].primaryType").type(JsonFieldType.STRING)
                                        .description("카테고리"),
                                fieldWithPath("data[].formattedAddress").type(JsonFieldType.STRING)
                                        .description("주소"),
                                fieldWithPath("data[].location").type(JsonFieldType.OBJECT)
                                        .description("위경도"),
                                fieldWithPath("data[].location.latitude").type(JsonFieldType.NUMBER)
                                        .description("위도"),
                                fieldWithPath("data[].location.longitude").type(JsonFieldType.NUMBER)
                                        .description("경도"),
                                fieldWithPath("data[].content").type(JsonFieldType.OBJECT)
                                        .description("장소 설명"),
                                fieldWithPath("data[].content.recommendationReason").type(JsonFieldType.STRING)
                                        .description("추천 이유"),
                                fieldWithPath("data[].content.activity").type(JsonFieldType.STRING)
                                        .description("추천 활동"),
                                fieldWithPath("data[].images").type(JsonFieldType.ARRAY)
                                        .description("장소 이미지"),
                                fieldWithPath("data[].images[].sequence").type(JsonFieldType.NUMBER)
                                        .description("이미지 순서"),
                                fieldWithPath("data[].images[].url").type(JsonFieldType.STRING)
                                        .description("이미지 url"),
                                fieldWithPath("data[].openingHours").type(JsonFieldType.ARRAY)
                                        .description("영업시간"),
                                fieldWithPath("data[].openNow").type(JsonFieldType.STRING)
                                        .description("현재 영업 여부 [OPEN, CLOSE, UNKNOWN]"),
                                fieldWithPath("data[].menuGroups[]").type(JsonFieldType.ARRAY)
                                        .description("메뉴 그룹"),
                                fieldWithPath("data[].menuGroups[].groupName").type(JsonFieldType.STRING)
                                        .description("메뉴 그룹명"),
                                fieldWithPath("data[].menuGroups[].menus[]").type(JsonFieldType.ARRAY)
                                        .description("메뉴 목록"),
                                fieldWithPath("data[].menuGroups[].menus[].menuName").type(JsonFieldType.STRING)
                                        .description("메뉴 이름"),
                                fieldWithPath("data[].menuGroups[].menus[].price").type(JsonFieldType.NUMBER)
                                        .description("메뉴 가격"),
                                fieldWithPath("data[].menuGroups[].menus[].description").type(JsonFieldType.STRING)
                                        .description("메뉴 설명")
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
                        .updatedAt(LocalDateTime.of(2024, 6, 3, 22, 19, 0))
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
                                        fieldWithPath("data.updatedAt").type(JsonFieldType.STRING)
                                                .description("마지막 수정 시간"),
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
                                                .description("현재 영업 여부 [OPEN, CLOSE, UNKNOWN]"),
                                        fieldWithPath("data.place.menuGroups[]").type(JsonFieldType.ARRAY)
                                                .description("메뉴 그룹"),
                                        fieldWithPath("data.place.menuGroups[].groupName").type(JsonFieldType.STRING)
                                                .description("메뉴 그룹명"),
                                        fieldWithPath("data.place.menuGroups[].menus[]").type(JsonFieldType.ARRAY)
                                                .description("메뉴 목록"),
                                        fieldWithPath("data.place.menuGroups[].menus[].menuName").type(JsonFieldType.STRING)
                                                .description("메뉴 이름"),
                                        fieldWithPath("data.place.menuGroups[].menus[].price").type(JsonFieldType.NUMBER)
                                                .description("메뉴 가격"),
                                        fieldWithPath("data.place.menuGroups[].menus[].description").type(JsonFieldType.STRING)
                                                .description("메뉴 설명")
                                )
                        )
                );
    }

    @DisplayName("추천 내역 조회 API")
    @MockUser
    @Test
    void getStatusRecommends() throws Exception {
        // given

        Recommend r1 = Recommend.builder()
                .id(1L)
                .userId(1L)
                .place(PlaceFixture.get("test"))
                .status(Recommend.Status.KEPT)
                .createdAt(LocalDateTime.of(2024, 6, 3, 22, 19, 0))
                .updatedAt(LocalDateTime.of(2024, 6, 3, 22, 19, 0))
                .build();
        Recommend r2 = Recommend.builder()
                .id(1L)
                .userId(1L)
                .place(PlaceFixture.get("test"))
                .status(Recommend.Status.COMPLETED)
                .createdAt(LocalDateTime.of(2024, 6, 3, 22, 19, 0))
                .updatedAt(LocalDateTime.of(2024, 6, 3, 22, 19, 0))
                .build();
        List<Recommend> recommends = List.of(r1, r2);

        given(recommendService.getRecommendsWithStatus(any(), any(), anyInt(), anyInt(), any())).willReturn(
                new SliceResult<>(recommends, 0, 10, 2, false)
        );

        // when & then
        mockMvc.perform(get("/api/v1/recommend/{status}", "%s,%s".formatted(Recommend.Status.KEPT, Recommend.Status.COMPLETED))
                        .param("page", "0")
                        .param("size", "10")
                        .param("language", "EN")
                        .header(AUTHORIZATION, ACCESS_TOKEN)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("recommend-get-status",
                                resourceDetails()
                                        .tag("recommend")
                                        .description("추천 내역 조회 API"),
                                requestHeaders(
                                        headerWithName(AUTHORIZATION).description(ACCESS_TOKEN).optional()
                                ),
                                pathParameters(
                                        parameterWithName("status").description("ACCEPTED, DENIED, KEPT, COMPLETED, REVOKED")
                                ),
                                queryParameters(
                                        parameterWithName("page").description("요청 페이지 Default 0").optional(),
                                        parameterWithName("size").description("요청 페이지 Default 10").optional(),
                                        parameterWithName("language").description("언어").optional()
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
                                        fieldWithPath("data.content[].updatedAt").type(JsonFieldType.STRING)
                                                .description("마지막 수정 시간"),
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
                                                .description("현재 영업 여부 [OPEN, CLOSE, UNKNOWN]"),
                                        fieldWithPath("data.content[].place.menuGroups[]").type(JsonFieldType.ARRAY)
                                                .description("메뉴 그룹"),
                                        fieldWithPath("data.content[].place.menuGroups[].groupName").type(JsonFieldType.STRING)
                                                .description("메뉴 그룹명"),
                                        fieldWithPath("data.content[].place.menuGroups[].menus[]").type(JsonFieldType.ARRAY)
                                                .description("메뉴 목록"),
                                        fieldWithPath("data.content[].place.menuGroups[].menus[].menuName").type(JsonFieldType.STRING)
                                                .description("메뉴 이름"),
                                        fieldWithPath("data.content[].place.menuGroups[].menus[].price").type(JsonFieldType.NUMBER)
                                                .description("메뉴 가격"),
                                        fieldWithPath("data.content[].place.menuGroups[].menus[].description").type(JsonFieldType.STRING)
                                                .description("메뉴 설명")
                                )
                        )
                );
    }

    @DisplayName("추천 상태 업데이트 API")
    @MockUser
    @Test
    void recommendStatusUpdate() throws Exception {
        // given & when & then
        mockMvc.perform(put("/api/v1/recommend/{status}?latitude=375912474&longitude=126.9184582", Recommend.Status.COMPLETED)
                        .header(AUTHORIZATION, ACCESS_TOKEN)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("recommend-put-status",
                                resourceDetails()
                                        .tag("recommend")
                                        .description("CHECK IN / GIVE UP API"),
                                requestHeaders(
                                        headerWithName(AUTHORIZATION).description(ACCESS_TOKEN).optional()
                                ),
                                pathParameters(
                                        parameterWithName("status").description("[COMPLETED, REVOKED]")
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
                                        fieldWithPath("data").type(JsonFieldType.NULL)
                                                .description("데이터")
                                )
                        )
                );
    }

    @DisplayName("진행중인 추천을 조회한다.")
    @MockUser
    @Test
    void retrieveProgressRecommend() throws Exception {
        // given
        given(recommendService.retrieveProgressRecommend(any(), any()))
                .willReturn(Recommend.builder()
                        .id(1L)
                        .userId(1L)
                        .place(PlaceFixture.get("test"))
                        .status(Recommend.Status.ACCEPTED)
                        .createdAt(LocalDateTime.of(2024, 6, 3, 22, 19, 0))
                        .updatedAt(LocalDateTime.of(2024, 6, 3, 22, 19, 0))
                        .build()
                );

        // when
        mockMvc.perform(get("/api/v1/recommend/progress")
                        .param("language", "EN")
                        .header(AUTHORIZATION, ACCESS_TOKEN)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("recommend-progress-get",
                        resourceDetails()
                                .tag("recommend")
                                .description("진행 중 추천 장소 조회 API"),
                        requestHeaders(
                                headerWithName(AUTHORIZATION).description(ACCESS_TOKEN)
                        ),
                        queryParameters(
                                parameterWithName("language").description("언어").optional()
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
                                fieldWithPath("data.updatedAt").type(JsonFieldType.STRING)
                                        .description("마지막 수정 시간"),
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
                                        .description("현재 영업 여부 [OPEN, CLOSE, UNKNOWN]"),
                                fieldWithPath("data.place.menuGroups[]").type(JsonFieldType.ARRAY)
                                        .description("메뉴 그룹"),
                                fieldWithPath("data.place.menuGroups[].groupName").type(JsonFieldType.STRING)
                                        .description("메뉴 그룹명"),
                                fieldWithPath("data.place.menuGroups[].menus[]").type(JsonFieldType.ARRAY)
                                        .description("메뉴 목록"),
                                fieldWithPath("data.place.menuGroups[].menus[].menuName").type(JsonFieldType.STRING)
                                        .description("메뉴 이름"),
                                fieldWithPath("data.place.menuGroups[].menus[].price").type(JsonFieldType.NUMBER)
                                        .description("메뉴 가격"),
                                fieldWithPath("data.place.menuGroups[].menus[].description").type(JsonFieldType.STRING)
                                        .description("메뉴 설명")
                        )
                        )

                );
    }
}