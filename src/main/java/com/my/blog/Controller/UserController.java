package com.my.blog.Controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.blog.Config.auth.PrincipalDetail;
import com.my.blog.Entity.KakaoUserProfile;
import com.my.blog.Entity.OAuthToken;
import com.my.blog.Entity.User;
import com.my.blog.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;



    private final String grant_type = "authorization_code";
    private final String client_id = "1b0c0bcb9eb7e69d5e2acd5c1a5e9926";
    private final String redirect_uri = "http://localhost:8080/auth/kakao/callback";

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @ResponseBody
    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code) {

        /** 카카오 엑세스 토큰 요청 START **/

        // POST방식으로 Key=Value 데이터를 요청 (카카오쪽으로)
        RestTemplate rt = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", grant_type);
        params.add("client_id", client_id);
        params.add("redirect_uri", redirect_uri);
        params.add("code", code);

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        // Http 요청하기 - POST방식으로 - 그리고 response 변수의 응답 받음
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        /** 카카오 엑세스 토큰 요청 END **/

        // Gson, Json Simple, ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oAuthToken = null;

        try {
            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        /** 카카오 엑세스 토큰으로 사용자정보 요청 START **/

        // POST방식으로 Key=Value 데이터를 요청 (카카오쪽으로)
        RestTemplate rt2 = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");


        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
                new HttpEntity<>(headers2);

        // Http 요청하기 - POST방식으로 - 그리고 response 변수의 응답 받음
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        System.out.println(response2.getBody());
        /** 카카오 엑세스 토큰으로 사용자정보 요청 END **/


        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoUserProfile kakaoUserProfile = null;

        try {
            kakaoUserProfile = objectMapper2.readValue(response2.getBody(), KakaoUserProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        log.info("카카오 아이디(번호)={}" ,kakaoUserProfile.getId());
        log.info("카카오사용자 유저네임={}", kakaoUserProfile.getKakao_account().getEmail()+"_"+kakaoUserProfile.getId());
        log.info("카카오 이메일={}", kakaoUserProfile.getKakao_account().getEmail());
        UUID garbagePWD = UUID.randomUUID();
        log.info("카카오 비밀번호={}", garbagePWD);


        User user =User.builder()
                .username(kakaoUserProfile.getKakao_account().getEmail()+"_"+kakaoUserProfile.getId())
                .password(garbagePWD.toString())
                .email(kakaoUserProfile.getKakao_account().getEmail())
                .build();

        userService.join(user);



        return  "회원가입 완료 : " + user.getUsername();
    }

    @GetMapping("/user/updateUser")
    public String updateUser(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        model.addAttribute("principal", principalDetail);
        return "user/updateUser";
    }


}
