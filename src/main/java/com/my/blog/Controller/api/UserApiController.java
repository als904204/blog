package com.my.blog.Controller.api;


import com.my.blog.Dto.ResponseDto;
import com.my.blog.Entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    // json 받음 = @RequestBody
    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user) {
        // 확인용 나중에 삭제
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());


        System.out.println("UserApiController save() 호출");
        return new ResponseDto<Integer>(HttpStatus.OK,1); // user.js res 에 1리턴
    }

}
