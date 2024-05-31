package com.questrip.reward.api.v1;

import com.questrip.reward.api.RestDocsTest;
import com.questrip.reward.api.v1.request.UserCreateRequest;
import com.questrip.reward.api.v1.request.UserEmailValidateRequest;
import com.questrip.reward.api.v1.request.UserLoginRequest;
import com.questrip.reward.domain.user.UserService;
import com.questrip.reward.domain.user.UserValidator;
import com.questrip.reward.fixture.UserFixture;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.resourceDetails;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends RestDocsTest {
    private final UserService userService = mock(UserService.class);
    @Override
    protected Object initializeController() {
        return new UserController(userService);
    }

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
                                        .description("토큰")
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
                                        .description("토큰")
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
}