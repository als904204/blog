package com.my.blog.test;

import com.my.blog.Repository.UserRepository;
import com.my.blog.Entity.User;
import com.my.blog.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/test")
@RestController
public class UserTestController {



    private final UserRepository userRepository;

    private final UserService userService;


    /**
    @PostMapping("/user/join1")
    public String join1(@RequestParam("username") String username,
                       @RequestParam("password") String password,
                       @RequestParam("email") String email) {
        log.info("username={}",username);
        log.info("password={}",password);
        log.info("email={}",email);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "join 테스트1";
    }
 **/

    /**
    @PostMapping("/user/join2")
    public String join2(User user) {
        log.info("username={}",user.getUsername());
        log.info("password={}",user.getPassword());
        log.info("email={}",user.getEmail());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "post 테스트";
    }

 **/
    @PostMapping("/user/join3")
    public String join3(User user) {
        log.info("username={}",user.getUsername());
        log.info("password={}",user.getPassword());
        log.info("email={}",user.getEmail());
        userService.join(user);
        return "post 테스트";
    }


}
