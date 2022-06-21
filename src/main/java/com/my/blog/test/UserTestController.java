package com.my.blog.test;

import com.my.blog.Repository.UserRepository;
import com.my.blog.Entity.User;
import com.my.blog.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/test/user")
@RestController
public class UserTestController {


    private final UserRepository userRepository;

    private final UserService userService;

    // 유저 회원가입
    @PostMapping("/join3")
    public String join3(User user) {
        log.info("username={}", user.getUsername());
        log.info("password={}", user.getPassword());
        log.info("email={}", user.getEmail());
        userService.join(user);
        return "post 테스트";
    }

    // id 로 유저 조회하기
    @GetMapping("/detail/{id}")
    public User detail(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("존재하지 않는 유저입니다 : " + id);
        });
        return user;
    }

    // 모든 유저 조회
    @GetMapping("/users")
    public List<User> userList() {
        return userRepository.findAll();
    }


    // 유저 삭제
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
           return id;
        }
        return id;
    }


    // 한페이지당 2개의 데이터 리턴
    @GetMapping()
    public Page<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return pagingUser;
    }

}
