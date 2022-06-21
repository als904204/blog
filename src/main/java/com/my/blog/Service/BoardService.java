package com.my.blog.Service;

import com.my.blog.Config.auth.PrincipalDetail;
import com.my.blog.Entity.Board;
import com.my.blog.Entity.User;
import com.my.blog.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
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

    @Transactional(readOnly = true)
    public Page<Board> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board BoardDetail(Long id) {
        return boardRepository.findById(id).orElseThrow(()-> {
            return new IllegalArgumentException("해당 ID 에 해당하는 글을 찾을 수 없습니다 : " + id);
        });
    }

    @Transactional
    public void deleteBoard(Long id, PrincipalDetail principal) {

        Board board = boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당 게시글을 찾지 못했습니다");
        });

        if (board.getUser().getId() != principal.getUser().getId()) {
            throw new IllegalStateException("해당 글을 삭제할 권한이 없습니다.");
        }

        boardRepository.deleteById(id);
        log.info("게시글 id={} 삭제 됨",id);
    }

    @Transactional
    public void updateForm(Long id, Board requestBoard) {
        Board board = boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당 게시글이 없습니다");
        });
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());

    }
}
