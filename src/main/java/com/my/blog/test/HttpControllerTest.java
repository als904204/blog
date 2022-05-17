package com.my.blog.test;

import com.my.blog.Entity.Member;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class HttpControllerTest {

    private static final String TAG = "HttpController";

    // /test/http/get?username=123&password=456
    @GetMapping("/http/get")
    public String getTest(Member member) {

        return "get 요청 : " + member.getId() + " name : " + member.getUsername() + member.getPassword();
    }

    /**
     * @PostMapping("/http/post") public String postTest(Member m) {
     * return m.toString();
     * }
     **/

    // json3
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m) {
        return m.toString();
    }

    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m) {
        return m.toString();
    }

    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }

    @GetMapping("/http/lombok")
    public String lombokTest() {
        Member m = Member.builder()
                .username("myName")
                .password("myPwd")
                .email("myEmail")
                .build();
        System.out.println(m.toString());
        return m.toString();
    }
}
