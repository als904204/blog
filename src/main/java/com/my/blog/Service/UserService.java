package com.my.blog.Service;

import com.my.blog.Entity.RoleType;
import com.my.blog.Entity.User;
import com.my.blog.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    // 트랜잭션 하나라도 실패 시 롤백
    // 가입 실패 시 -1L 리턴
    // 성공 시 1L
    @Transactional
    public Long join(User user) {
        validateDuplicateUser(user);

        try {
            user.setRole(RoleType.USER);
            userRepository.save(user);
            return 1L;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1L;
    }

    private void validateDuplicateUser(User user) {
        userRepository.findByUsername(user.getUsername()).ifPresent(u -> {
            throw new IllegalArgumentException("이미 존재하는 회원입니다");
        });
    }

    // 업데이트
    @Transactional
    public User update(User RequestUser, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 ID 유저를  찾을 수 없습니다");
                }
        );
        user.setPassword(RequestUser.getPassword());
        user.setEmail(RequestUser.getEmail());
        return user;
    }


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Transactional(readOnly=true)
    public User login(User user){
        User nullUser= userRepository.findByUsername(user.getUsername()).orElseThrow(() ->{
                    throw new IllegalArgumentException("해당 ID 유저를 찾을 수 없습니다");
          }
        );
        return userRepository.login(user.getUsername(), user.getPassword());
    }
}
