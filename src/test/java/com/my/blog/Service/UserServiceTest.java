package com.my.blog.Service;

import com.my.blog.Entity.User;
import com.my.blog.Repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class UserServiceTest {


    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;



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
                () -> userService.join(user2));

        //then
        Assertions.assertThat(returnStatusMessage.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }

    @Test
    void 모든유저조회() {
        int userNum = 30;
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userList.add(User.builder()
                    .username("user" + ++userNum)
                    .password("pwd" + ++userNum)
                    .email("email" + ++userNum)
                    .build());
        }

        Iterator<User> iterator = userList.iterator();

        while (iterator.hasNext()) {
            userService.join(iterator.next());
        }

        List<User> findAll = userRepository.findAll();

        Assertions.assertThat(findAll.size()).isEqualTo(10);
        Assertions.assertThat(findAll.size()).isNotEqualTo(100);
    }

    @Test
    void 유저_업데이트() {
        User user1 = new User();
        user1.setUsername("testUpdateName");
        user1.setPassword("testUpdatePwd");
        user1.setEmail("testUpdateEmail");

        userService.join(user1);


        User updateUser = userService.update(User.builder()
                    .password("updatePWD")
                    .email("updateEmail")
                    .build()
                ,1L);

        Assertions.assertThat(user1.getUsername()).isEqualTo(updateUser.getUsername());
    }

    @Test
    void 유저_삭제() {

        // given
        User user1 = new User();
        user1.setUsername("testUpdateName1");
        user1.setPassword("testUpdatePwd1");
        user1.setEmail("testUpdateEmail1");
        userService.join(user1);

        User user2 = new User();
        user2.setUsername("testUpdateName2");
        user2.setPassword("testUpdatePwd2");
        user2.setEmail("testUpdateEmail2");
        userService.join(user2);

        // when
        userRepository.deleteById(2L);
        List<User> findAll = userRepository.findAll();

        // then
        Assertions.assertThat(findAll.size()).isEqualTo(1);
    }
}
