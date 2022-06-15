package com.my.blog.Controller;

import com.my.blog.Config.auth.PrincipalDetail;
import com.my.blog.Service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping({"", "/"})
    public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boardList", boardService.boardList(pageable));
        return "index";
    }

    @GetMapping("/auth/board/{id}")
    public String BoardDetail(@PathVariable Long id, Model model) {
        model.addAttribute("boardDetail", boardService.BoardDetail(id));
        return "board/detail";

    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }


}
