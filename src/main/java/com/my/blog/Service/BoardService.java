package com.my.blog.Service;

import com.my.blog.Config.auth.PrincipalDetail;
import com.my.blog.Entity.Board;
import com.my.blog.Entity.RoleType;
import com.my.blog.Entity.User;
import com.my.blog.Repository.BoardRepository;
import com.my.blog.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {


    private final BoardRepository boardRepository;

    // 글쓰기
    @Transactional
    public void saveBoard(Board board, User user) {
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    public Page<Board> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }


    public Board BoardDetail(Long id) {
        return boardRepository.findById(id).orElseThrow(()-> {
            return new IllegalArgumentException("해당 ID 에 해당하는 글을 찾을 수 없습니다 : " + id);
        });
    }
}
