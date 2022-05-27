package com.my.blog.Controller.api;


import com.my.blog.Dto.ResponseDto;
import com.my.blog.Entity.User;
import com.my.blog.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    // json 받음 = @RequestBody
    @PostMapping("/api/user")
    public ResponseDto<Long> save(@RequestBody User user) {
        Long result = userService.join(user);
        log.info("UserApiController.class : save() 호출");
        return new ResponseDto<Long>(HttpStatus.OK.value(),result); // user.js res 에 1리턴
    }

}
