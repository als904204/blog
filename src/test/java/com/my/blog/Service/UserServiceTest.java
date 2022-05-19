package com.my.blog.Service;

import com.my.blog.Entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class UserServiceTest {



    @Autowired
    UserService userService;


    @Test
    void 회원가입() {
        // given
        Long user = userService.join(User.builder()
                .username("testUsername")
                .password("testPwd")
                .email("testEmail")
                .build());

        // when
        Long result = userService.findByUsername("testUsername").get().getId();

        // then
        Assertions.assertThat(user).isEqualTo(result);


    }

    @Test
    void 중복회원예외() {

        // given
        User user1 = new User();
        user1.setUsername("testUsername");
        user1.setPassword("testPwd");
        user1.setEmail("testEmail");
        userService.join(user1);


        // when
        User user2 = new User();
        user2.setUsername("testUsername");
        user2.setPassword("testPwd");
        user2.setEmail("testEmail");

        IllegalArgumentException returnStatusMessage = assertThrows(IllegalArgumentException.class,
                ()-> userService.join(user2));

        //then
        Assertions.assertThat(returnStatusMessage.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }
}