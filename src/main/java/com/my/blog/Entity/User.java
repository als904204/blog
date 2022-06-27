package com.my.blog.Entity;

import lombok.*;

import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert // null 인 값은 insert 안함
@Entity // User 클래스가 MySql 에 테이블이 생성 됨
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length =40,unique = true)
    private String username;


    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false)
    private String email;

    // 시간 자동 입력
    @CreationTimestamp
    private Timestamp createDate;


    // DB 에 해당 타입이 String 로 인식하게 해줌
    @Enumerated(EnumType.STRING)
    private RoleType role;


}
