package com.my.blog.test;

import com.my.blog.Entity.Member;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class HttpControllerTest {

    // /test/http/get?username=123&password=456
    @GetMapping("/http/get")
    public String getTest(Member member) {
        return "get 요청 : " + member.getId() + " name : " + member.getUsername() + member.getPassword();
    }

    /**
     @PostMapping("/http/post")
    public String postTest(Member m) {
        return m.toString();
    }
    **/

    // json
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m ) {
        return m.toString();
    }

     @PutMapping ("/http/put")
    public String putTest(@RequestBody Member m) {
        return m.toString() ;
    }
     @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }

}
