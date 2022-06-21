package com.my.blog.Controller.api;


import com.my.blog.Config.auth.PrincipalDetail;
import com.my.blog.Dto.ResponseDto;
import com.my.blog.Entity.User;
import com.my.blog.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Log4j2
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    // json 받음 = @RequestBody
    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        userService.join(user);
        log.info("UserApiController.class : save() 호출");
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // user.js res 에 1리턴
    }


    @PutMapping("/user")
    public ResponseDto<Integer> updateUser(@RequestBody User user) {
        userService.update(user);

        // 수정된 정보 세션 등록
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // user.js res 에 1리턴

    }
}
