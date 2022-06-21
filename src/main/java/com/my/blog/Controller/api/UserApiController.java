package com.my.blog.Controller.api;


import com.my.blog.Config.auth.PrincipalDetail;
import com.my.blog.Dto.ResponseDto;
import com.my.blog.Entity.User;
import com.my.blog.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Log4j2
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

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
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // user.js res 에 1리턴

    }
}
