package com.my.blog.Entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Lob // 대용량 데이터
    private String content;

    // 조회수
    private int count;

    /** 수정 **/
    // Many : Board
    // One : User
    // 한명의 유저는 여러개의 게시글 작성 가능
    @ManyToOne(fetch = FetchType.EAGER) // Board 테이블을 조회할 때 유저 ID 값을 무조건 가져와야 한다
    @JoinColumn(name = "userId") // 필드 값 user 가 아니라 userId 로 저장
    private User user; // DB 는 오브젝트를 저장 할 수 없다. 자바는 오브젝트를 저장할 수 있다


    /** 수정 **/
    /** JoinColumn 으로 FK 설정 안하는 이유
     * =============== 답변테이블을 FK로 안했을 때 ==================
     * ----------------------------------------------------
     * | 게시글번호 | 제목  | 내용    | 유저ID  | 게시글작성시간 |
     * |  1      | 호우  | 쑤우우  |     1  | 1557.15.57   |
     * ---------------------------------------------------
     *
     * =============== 답변테이블을 FK 로 두었을 때 ==================
     *
     * ------------------------------------------------------------
     * | 게시글번호 | 제목  | 내용    | 유저ID  | 답변ID | 게시글작성시간 |
     * |  1      | 호우  | 쑤우우  |     1  |    1,2  |1557.15.57  |
     * -----------------------------------------------------------
     * 한 유저가 작성한 게시글에 여러개의 답변이 작성된다면 답변ID에 1,2,3....n 이 들어가야 되는데 이는 DB 의 정규화가 깨진다
     * 정규화 : 컬럼에는 하나의 원자성만 갖는다
    * **/
    // 한 게시글에 여러개의 답변이 올 수 있기 때문에 List 로 가져옴
    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER) // FK 키가 아님, DB에 컬럼 만들기 X
   private List<Reply> reply;



    @CreationTimestamp
    private Timestamp createDate;
}
