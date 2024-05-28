package com.questrip.reward.api.v1;

import com.questrip.reward.api.RestDocsTest;
import com.questrip.reward.domain.direction.DirectionSummary;
import com.questrip.reward.domain.place.PlaceAndDirection;
import com.questrip.reward.domain.place.PlaceService;
import com.questrip.reward.fixture.PlaceFixture;
import com.questrip.reward.support.response.SliceResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PlaceControllerTest extends RestDocsTest {

    private final PlaceService placeService = mock(PlaceService.class);

    @Override
    protected Object initializeController() {
        return new PlaceController(placeService);
    }

    @DisplayName("장소 등록 API")
    @Test
    void create() throws Exception {
        // given
        given(placeService.save(any(), any(), any()))
                .willReturn(
                        PlaceFixture.get("6633897aa2757d5b1998ba0d")
                );

        // when & then
        mockMvc.perform(post("/api/v1/place")
                        .param("googlePlaceId", "6633897aa2757d5b1998ba0d")
                        .param("recommendationReason", "test")
                        .param("activity", "test")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("place-create",
                        formParameters(
                                parameterWithName("googlePlaceId").description("구글 플레이스 아이디"),
                                parameterWithName("recommendationReason").description("추천 이유"),
                                parameterWithName("activity").description("추천 활동")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("데이터"),
                                fieldWithPath("data.id").type(JsonFieldType.STRING)
                                        .description("장소 아이디"),
                                fieldWithPath("data.googlePlaceId").type(JsonFieldType.STRING)
                                        .description("구글 장소 아이디"),
                                fieldWithPath("data.placeName").type(JsonFieldType.STRING)
                                        .description("장소 이름"),
                                fieldWithPath("data.primaryType").type(JsonFieldType.STRING)
                                        .description("카테고리"),
                                fieldWithPath("data.formattedAddress").type(JsonFieldType.STRING)
                                        .description("주소"),
                                fieldWithPath("data.location").type(JsonFieldType.OBJECT)
                                        .description("위경도"),
                                fieldWithPath("data.location.latitude").type(JsonFieldType.NUMBER)
                                        .description("위도"),
                                fieldWithPath("data.location.longitude").type(JsonFieldType.NUMBER)
                                        .description("경도"),
                                fieldWithPath("data.content").type(JsonFieldType.OBJECT)
                                        .description("장소 설명"),
                                fieldWithPath("data.content.recommendationReason").type(JsonFieldType.STRING)
                                        .description("추천 이유"),
                                fieldWithPath("data.content.activity").type(JsonFieldType.STRING)
                                        .description("추천 활"),
                                fieldWithPath("data.images").type(JsonFieldType.ARRAY)
                                        .description("장소 이미지"),
                                fieldWithPath("data.images[].sequence").type(JsonFieldType.NUMBER)
                                        .description("이미지 순서"),
                                fieldWithPath("data.images[].url").type(JsonFieldType.STRING)
                                        .description("이미지 url"),
                                fieldWithPath("data.openingHours").type(JsonFieldType.ARRAY)
                                        .description("영업시간"),
                                fieldWithPath("data.openNow").type(JsonFieldType.STRING)
                                        .description("현재 영업 여부 [OPEN, CLOSE, UNKNOWN]")
                        )

                ));
    }

    @DisplayName("장소 찾기 API")
    @Test
    void findOne() throws Exception {
        // given
        given(placeService.findPlaceWithDirectionSummary(any(), any()))
                .willReturn(
                        new PlaceAndDirection(
                                PlaceFixture.get("6633897aa2757d5b1998ba0d"),
                                new DirectionSummary("27.1 km", "1 hour 16 mins")
                        )
                );


        // when & then
        mockMvc.perform(get("/api/v1/place/{placeId}", "6633897aa2757d5b1998ba0d")
                        .param("latitude", "37.5912474")
                        .param("longitude", "126.9184582")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("place-get",
                        pathParameters(
                                parameterWithName("placeId").description("플레이스 아이디(구글 x)")
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
                                fieldWithPath("data.place").type(JsonFieldType.OBJECT)
                                        .description("플레이스"),
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
                                        .description("추천 활"),
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
                                fieldWithPath("data.directionSummary").type(JsonFieldType.OBJECT)
                                        .description("길찾기 요약 정보"),
                                fieldWithPath("data.directionSummary.distance").type(JsonFieldType.STRING)
                                        .description("현재 위치에서 장소까지 거리(km)"),
                                fieldWithPath("data.directionSummary.duration").type(JsonFieldType.STRING)
                                        .description("현재 위치에서 장소까지 시간")
                        )
                ));
    }

    @DisplayName("장소 리스트 API")
    @Test
    void findAll() throws Exception {
        // given
        given(placeService.findAllPlaceNear(any(), anyInt(), anyInt()))
                .willReturn(
                        new SliceResult<>(List.of(PlaceFixture.get("6633897aa2757d5b1998ba0d")), 0, 10, 1, false)
                );

        // when & then
        mockMvc.perform(get("/api/v1/place")
                        .param("latitude", "37.5912474")
                        .param("longitude", "126.9184582")
                        .param("page", "0")
                        .param("size", "10")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("place-list-get",
                        queryParameters(
                                parameterWithName("latitude").description("유저 위도"),
                                parameterWithName("longitude").description("유저 경도"),
                                parameterWithName("page").description("요청 페이지 (default 0)"),
                                parameterWithName("size").description("요청 사이즈 (default 10)")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("데이터"),
                                fieldWithPath("data.content").type(JsonFieldType.ARRAY)
                                        .description("데이터"),
                                fieldWithPath("data.content[].id").type(JsonFieldType.STRING)
                                        .description("장소 아이디"),
                                fieldWithPath("data.content[].googlePlaceId").type(JsonFieldType.STRING)
                                        .description("구글 장소 아이디"),
                                fieldWithPath("data.content[].placeName").type(JsonFieldType.STRING)
                                        .description("장소 이름"),
                                fieldWithPath("data.content[].primaryType").type(JsonFieldType.STRING)
                                        .description("카테고리"),
                                fieldWithPath("data.content[].formattedAddress").type(JsonFieldType.STRING)
                                        .description("주소"),
                                fieldWithPath("data.content[].location").type(JsonFieldType.OBJECT)
                                        .description("위경도"),
                                fieldWithPath("data.content[].location.latitude").type(JsonFieldType.NUMBER)
                                        .description("위도"),
                                fieldWithPath("data.content[].location.longitude").type(JsonFieldType.NUMBER)
                                        .description("경도"),
                                fieldWithPath("data.content[].content").type(JsonFieldType.OBJECT)
                                        .description("장소 설명"),
                                fieldWithPath("data.content[].content.recommendationReason").type(JsonFieldType.STRING)
                                        .description("추천 이유"),
                                fieldWithPath("data.content[].content.activity").type(JsonFieldType.STRING)
                                        .description("추천 활"),
                                fieldWithPath("data.content[].images").type(JsonFieldType.ARRAY)
                                        .description("장소 이미지"),
                                fieldWithPath("data.content[].images[].sequence").type(JsonFieldType.NUMBER)
                                        .description("이미지 순서"),
                                fieldWithPath("data.content[].images[].url").type(JsonFieldType.STRING)
                                        .description("이미지 url"),
                                fieldWithPath("data.content[].openingHours").type(JsonFieldType.ARRAY)
                                        .description("영업시간"),
                                fieldWithPath("data.content[].openNow").type(JsonFieldType.STRING)
                                        .description("현재 영업 여부 [OPEN, CLOSE, UNKNOWN]"),
                                fieldWithPath("data.content[].distance").type(JsonFieldType.NUMBER)
                                        .description("현재 위치에서 장소까지 거리(km)"),
                                fieldWithPath("data.page").type(JsonFieldType.NUMBER)
                                        .description("요청 페이지"),
                                fieldWithPath("data.size").type(JsonFieldType.NUMBER)
                                        .description("요청 사이즈"),
                                fieldWithPath("data.numberOfElements").type(JsonFieldType.NUMBER)
                                        .description("실제 데이터 개수"),
                                fieldWithPath("data.hasNext").type(JsonFieldType.BOOLEAN)
                                        .description("다음 페이지 존재 여부")
                        )
                ));
    }

    @DisplayName("역지오코딩 API")
    @Test
    void reverseGeocode() throws Exception {
        // given
        given(placeService.reverseGeocode(any())).willReturn(
                "226-19 Jamsil-dong, Songpa District, Seoul, South Korea"
        );

        // when & then
        mockMvc.perform(get("/api/v1/place/reverseGeocode")
                        .param("latitude", "37.5068006")
                        .param("longitude", "127.0830179")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("place-geocode",
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
                                fieldWithPath("data.formattedAddress").type(JsonFieldType.STRING)
                                        .description("주소")
                        )
                ));

    }
}