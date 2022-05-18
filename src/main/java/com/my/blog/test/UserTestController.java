package com.my.blog.test;

import com.my.blog.Entity.RoleType;
import com.my.blog.Entity.User;
import com.my.blog.Repository.UserRepository;
import com.my.blog.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/test")
@RestController
public class UserTestController {

    private final UserService userService;

    /**
    @PostMapping("/user/join")
    public String join(@RequestParam("username") String username,
                       @RequestParam("password") String password,
                       @RequestParam("email") String email) {
        log.info("username={}",username);
        log.info("password={}",password);
        log.info("email={}",email);
        return "h";
    }
    **/

    /**
    @PostMapping("/user/join")
    public String join(User user) {
        log.info("username={}",user.getUsername());
        log.info("password={}",user.getPassword());
        log.info("email={}",user.getEmail());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "post 테스트";
    }
    **/

    @PostMapping("/user/join")
    public String join(User user) {
        log.info("username={}",user.getUsername());
        log.info("password={}",user.getPassword());
        log.info("email={}",user.getEmail());



        userService.join(user);
        return "post 테스트";
    }


}
