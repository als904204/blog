package com.my.blog.Controller;

import com.my.blog.Config.auth.PrincipalDetail;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class BoardController {


    @GetMapping({"", "/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principal) {
        log.info("사용자 아이디 : {} ", principal.getUsername());
        return "index";
    }
}
