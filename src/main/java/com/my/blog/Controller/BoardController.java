package com.my.blog.Controller;

import com.my.blog.Config.auth.PrincipalDetail;
import com.my.blog.Service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping({"", "/"})
    public String index(Model model) {
        model.addAttribute("boardList", boardService.boardList());
        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }
}
