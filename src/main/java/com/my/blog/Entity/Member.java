package com.my.blog.Entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Member {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    public Member() {

    }


    // Member 객체를 생성할 때 id 를 제외한 나머지 값을 프로퍼티 접근을 해야 함
    // 기본 생성자에는 id 가 있기 때문에 id 값도 넣어줘야 함
    // 필더 패턴을 사용하면 내가 원하는 파라매터에만 값을 넣어주면 됨 ( 순서도 상관 없음 )
    @Builder
    public Member(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

}
