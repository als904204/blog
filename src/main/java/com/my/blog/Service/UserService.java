package com.my.blog.Service;

import com.my.blog.Entity.RoleType;
import com.my.blog.Entity.User;
import com.my.blog.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService  {

    private final UserRepository userRepository;

    public Long join(User user) {
        validateDuplicateUser(user);
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user) {
        userRepository.findByUsername(user.getUsername()).ifPresent(u ->{
            throw new IllegalArgumentException("이미 존재하는 회원입니다");
        });
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
