package com.my.blog.Controller;


import com.my.blog.Config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateUser")
    public String updateUser(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        model.addAttribute("principal", principalDetail);
        return "user/updateUser";
    }
}
