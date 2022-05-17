package com.my.blog.test;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

    @GetMapping("/temp/jsp")
    public String tempJSP() {
        System.out.println("tempJSP()");
        // main/webapp/WEB-INF/views/FileName.jsp
        return "test";
    }
}
