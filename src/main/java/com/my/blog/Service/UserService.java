package com.my.blog.Service;

import com.my.blog.Entity.RoleType;
import com.my.blog.Entity.User;
import com.my.blog.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;


    private final AuthenticationManager authenticationManager;

    // 트랜잭션 하나라도 실패 시 롤백
    // 가입 실패 시 -1L 리턴
    // 성공 시 1L
    @Transactional
    public void join(User user) {
        validateDuplicateUser(user);
        String rawPassword = user.getPassword(); // Original Password
        String encPassword = encoder.encode(rawPassword); // hash Password
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    private void validateDuplicateUser(User user) {
        userRepository.findByUsername(user.getUsername()).ifPresent(u -> {

            throw new IllegalArgumentException("이미 존재하는 회원입니다");
        });
    }

    // 유저 정보 수정
    @Transactional
    public void update(User user) {
        // 영속화 유저
        User persistanceUser = userRepository.findById(user.getId()).orElseThrow(() -> {
            return new IllegalArgumentException("해당 유저를 찾을 수 없습니다");
        });

        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);

        persistanceUser.setPassword(encPassword);
        persistanceUser.setEmail(user.getEmail());

    }


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }



}
