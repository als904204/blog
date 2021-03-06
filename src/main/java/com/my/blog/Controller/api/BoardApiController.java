package com.my.blog.Controller.api;


import com.my.blog.Config.auth.PrincipalDetail;
import com.my.blog.Dto.ResponseDto;
import com.my.blog.Entity.Board;
import com.my.blog.Entity.User;
import com.my.blog.Service.BoardService;
import com.my.blog.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
public class BoardApiController {


    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> saveBoard(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.saveBoard(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteBoard(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.deleteBoard(id, principal);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> updateForm(@PathVariable Long id, @RequestBody Board board) {
        boardService.updateForm(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
