package com.my.blog.test;

import com.my.blog.Entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class javaTestcode {
    public static void main(String[] args) {

        해시();
    }
    static void 해시(){
        String pwd = new BCryptPasswordEncoder().encode("1234");
        System.out.println(pwd);
    }
}


