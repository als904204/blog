package com.my.blog.test;

import com.my.blog.Repository.UserRepository;
import com.my.blog.Entity.User;
import com.my.blog.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/test")
@RestController
public class UserTestController {


    private final UserRepository userRepository;

    private final UserService userService;


    @PostMapping("/user/join3")
    public String join3(User user) {
        log.info("username={}", user.getUsername());
        log.info("password={}", user.getPassword());
        log.info("email={}", user.getEmail());
        userService.join(user);
        return "post 테스트";
    }

    @GetMapping("/user/detail/{id}")
    public User detail(@PathVariable Long id) {
        /**

        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("존재하지 않는 유저입니다 : " + id);
            }
        **/

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("존재하지 않는 유저입니다 : " + id);
        });


       return user;
    }
}
