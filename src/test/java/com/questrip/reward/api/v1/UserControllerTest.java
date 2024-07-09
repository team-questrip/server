package com.questrip.reward.api.v1;

import com.questrip.reward.api.RestDocsTest;
import com.questrip.reward.api.v1.request.UserCreateRequest;
import com.questrip.reward.api.v1.request.UserEmailValidateRequest;
import com.questrip.reward.api.v1.request.UserLoginRequest;
import com.questrip.reward.api.v1.request.UserPreferenceRequest;
import com.questrip.reward.domain.user.UserPreference;
import com.questrip.reward.domain.user.UserService;
import com.questrip.reward.fixture.UserFixture;
import com.questrip.reward.mockuser.MockUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.resourceDetails;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest extends RestDocsTest {

    @MockBean
    UserService userService;

    @DisplayName("회원가입 API")
    @Test
    void register() throws Exception {
        // given
        UserCreateRequest request = new UserCreateRequest("harok", "harok@questrips.com", "harok123");
        given(userService.register(any())).willReturn(UserFixture.getUserWithToken());

        // when & then
        mockMvc.perform(post("/api/v1/user/join")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("user-create",
                        resourceDetails()
                                .tag("user")
                                .description("회원가입 API"),
                        requestFields(
                                fieldWithPath("username").type(JsonFieldType.STRING)
                                        .description("유저 이름"),
                                fieldWithPath("email").type(JsonFieldType.STRING)
                                        .description("유저 메일"),
                                fieldWithPath("password").type(JsonFieldType.STRING)
                                        .description("비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("데이터"),
                                fieldWithPath("data.user").type(JsonFieldType.OBJECT)
                                        .description("유저"),
                                fieldWithPath("data.user.id").type(JsonFieldType.NUMBER)
                                        .description("유저 아이디"),
                                fieldWithPath("data.user.username").type(JsonFieldType.STRING)
                                        .description("유저 이름"),
                                fieldWithPath("data.user.email").type(JsonFieldType.STRING)
                                        .description("유저 이메일"),
                                fieldWithPath("data.user.role").type(JsonFieldType.STRING)
                                        .description("유저 권한"),
                                fieldWithPath("data.accessToken").type(JsonFieldType.STRING)
                                        .description("액세스 토큰"),
                                fieldWithPath("data.refreshToken").type(JsonFieldType.STRING)
                                        .description("리프레시 토큰")
                        )
                ));
    }

    @DisplayName("로그인 API")
    @Test
    void login() throws Exception {
        // given
        UserLoginRequest request = new UserLoginRequest("harok@questrips.com", "harok123");
        given(userService.login(any(), any())).willReturn(UserFixture.getUserWithToken());

        // when
        mockMvc.perform(post("/api/v1/user/login")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("user-login",
                        resourceDetails()
                                .tag("user")
                                .description("로그인 API"),
                        requestFields(
                                fieldWithPath("email").type(JsonFieldType.STRING)
                                        .description("유저 메일"),
                                fieldWithPath("password").type(JsonFieldType.STRING)
                                        .description("비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("데이터"),
                                fieldWithPath("data.user").type(JsonFieldType.OBJECT)
                                        .description("유저"),
                                fieldWithPath("data.user.id").type(JsonFieldType.NUMBER)
                                        .description("유저 아이디"),
                                fieldWithPath("data.user.username").type(JsonFieldType.STRING)
                                        .description("유저 이름"),
                                fieldWithPath("data.user.email").type(JsonFieldType.STRING)
                                        .description("유저 이메일"),
                                fieldWithPath("data.user.role").type(JsonFieldType.STRING)
                                        .description("유저 권한"),
                                fieldWithPath("data.accessToken").type(JsonFieldType.STRING)
                                        .description("토큰"),
                                fieldWithPath("data.refreshToken").type(JsonFieldType.STRING)
                                        .description("리프레시 토큰")
                        )
                ));
    }

    @DisplayName("이메일 중복검사 API")
    @Test
    void checkEmail() throws Exception {
        // given
        UserEmailValidateRequest request = new UserEmailValidateRequest("harok@questrips.com");

        // when
        mockMvc.perform(post("/api/v1/user/checkEmail")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("user-checkEmail",
                        resourceDetails()
                                .tag("user")
                                .description("이메일 중복검사 API"),
                        requestFields(
                                fieldWithPath("email").type(JsonFieldType.STRING)
                                        .description("유저 메일")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.NULL)
                                        .description("데이터")
                        )
                ));
    }

    @DisplayName("토큰 재발행 API")
    @Test
    void reissue() throws Exception {
        // given
        given(userService.reIssue(any())).willReturn(UserFixture.getUserWithToken());

        // when
        mockMvc.perform(post("/api/v1/user/reissue")
                        .header(AUTHORIZATION, REFRESH_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("user-reissue",
                        resourceDetails()
                                .tag("user")
                                .description("토큰 재발행 API"),
                        requestHeaders(
                                headerWithName(AUTHORIZATION).description(REFRESH_TOKEN)
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.NULL)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("데이터"),
                                fieldWithPath("data.user").type(JsonFieldType.OBJECT)
                                        .description("유저"),
                                fieldWithPath("data.user.id").type(JsonFieldType.NUMBER)
                                        .description("유저 아이디"),
                                fieldWithPath("data.user.username").type(JsonFieldType.STRING)
                                        .description("유저 이름"),
                                fieldWithPath("data.user.email").type(JsonFieldType.STRING)
                                        .description("유저 이메일"),
                                fieldWithPath("data.user.role").type(JsonFieldType.STRING)
                                        .description("유저 권한"),
                                fieldWithPath("data.accessToken").type(JsonFieldType.STRING)
                                        .description("토큰"),
                                fieldWithPath("data.refreshToken").type(JsonFieldType.STRING)
                                        .description("리프레시 토큰")
                        )
                ));
    }

    @DisplayName("유저 선호도를 가져온다")
    @MockUser
    @Test
    void getPreference() throws Exception {
        // given
        given(userService.getPreference(any()))
                .willReturn(UserPreference.defaultPreference(1L));

        // when & then
        mockMvc.perform(get("/api/v1/user/preference")
                        .header(AUTHORIZATION, ACCESS_TOKEN)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("user-preference-get",
                        resourceDetails()
                                .tag("user")
                                .description("유저 선호도 조회 API"),
                        requestHeaders(
                                headerWithName(AUTHORIZATION).description(ACCESS_TOKEN)
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.NULL)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("데이터"),
                                fieldWithPath("data.groupSize").type(JsonFieldType.NUMBER)
                                        .description("그룹 사이즈"),
                                fieldWithPath("data.travelingWithKid").type(JsonFieldType.BOOLEAN)
                                        .description("아이와 함께 여행"),
                                fieldWithPath("data.distance").type(JsonFieldType.NUMBER)
                                        .description("거리"),
                                fieldWithPath("data.dietary").type(JsonFieldType.OBJECT)
                                        .description("음식 선호"),
                                fieldWithPath("data.dietary.vegetarian").type(JsonFieldType.BOOLEAN)
                                        .description("베지테리안"),
                                fieldWithPath("data.dietary.nonAlcoholic").type(JsonFieldType.BOOLEAN)
                                        .description("알콜 섭취"),
                                fieldWithPath("data.dietary.seafoodRestrictions").type(JsonFieldType.BOOLEAN)
                                        .description("해산물"),
                                fieldWithPath("data.dietary.noSpicyFood").type(JsonFieldType.BOOLEAN)
                                        .description("매운 음식")
                        )
                ));
    }

    @DisplayName("유저 선호도를 저장한다")
    @MockUser
    @Test
    void savePreference() throws Exception {
        // given
        given(userService.savePreference(any()))
                .willReturn(UserPreference.defaultPreference(1L));

        UserPreferenceRequest request = new UserPreferenceRequest(1, false, false, false, false, false, 5);
        // when & then
        mockMvc.perform(post("/api/v1/user/preference")
                        .header(AUTHORIZATION, ACCESS_TOKEN)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("user-preference-post",
                        resourceDetails()
                                .tag("user")
                                .description("유저 선호도 추가 API"),
                        requestHeaders(
                                headerWithName(AUTHORIZATION).description(ACCESS_TOKEN)
                        ),
                        requestFields(
                                fieldWithPath("groupSize").type(JsonFieldType.NUMBER)
                                        .description("그룹 사이즈"),
                                fieldWithPath("travelingWithKid").type(JsonFieldType.BOOLEAN)
                                        .description("아이와 함께 여행"),
                                fieldWithPath("distance").type(JsonFieldType.NUMBER)
                                        .description("거리"),
                                fieldWithPath("vegetarian").type(JsonFieldType.BOOLEAN)
                                        .description("베지테리안"),
                                fieldWithPath("nonAlcoholic").type(JsonFieldType.BOOLEAN)
                                        .description("알콜 섭취"),
                                fieldWithPath("seafoodRestrictions").type(JsonFieldType.BOOLEAN)
                                        .description("해산물"),
                                fieldWithPath("noSpicyFood").type(JsonFieldType.BOOLEAN)
                                        .description("매운 음식")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("응답 상태"),
                                fieldWithPath("message").type(JsonFieldType.NULL)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("데이터"),
                                fieldWithPath("data.groupSize").type(JsonFieldType.NUMBER)
                                        .description("그룹 사이즈"),
                                fieldWithPath("data.travelingWithKid").type(JsonFieldType.BOOLEAN)
                                        .description("아이와 함께 여행"),
                                fieldWithPath("data.distance").type(JsonFieldType.NUMBER)
                                        .description("거리"),
                                fieldWithPath("data.dietary").type(JsonFieldType.OBJECT)
                                        .description("음식 선호"),
                                fieldWithPath("data.dietary.vegetarian").type(JsonFieldType.BOOLEAN)
                                        .description("베지테리안"),
                                fieldWithPath("data.dietary.nonAlcoholic").type(JsonFieldType.BOOLEAN)
                                        .description("알콜 섭취"),
                                fieldWithPath("data.dietary.seafoodRestrictions").type(JsonFieldType.BOOLEAN)
                                        .description("해산물"),
                                fieldWithPath("data.dietary.noSpicyFood").type(JsonFieldType.BOOLEAN)
                                        .description("매운 음식")
                        )
                ));
    }
}