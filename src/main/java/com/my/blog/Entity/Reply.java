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
@Entity //
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 답변내용
    @Column(nullable = false)
    private String content;

    @ManyToOne // 여러 개의 답변은 한 게시글에 존재 가능
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne // 여러 개의 답변을 한명의 유저만 작성 할 수 있다(두의 유저가 하나의 게시글을 작성X)
    @JoinColumn(name = "userId")
    private User user;

    @CreationTimestamp
    private Timestamp timestamp;

}
