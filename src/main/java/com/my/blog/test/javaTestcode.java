package com.my.blog.test;

import com.my.blog.Entity.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class javaTestcode {
    public static void main(String[] args) {
        int userNum = 0;
        List<User> userList = new ArrayList<>();




        for (int i = 0; i < 10; i++) {
            userList.add(User.builder()
                    .username("user" + ++userNum)
                    .password("pwd" + ++userNum)
                    .email("email" + ++userNum)
                    .build());
        }


        for (int i = 0; i < userList.size(); i++) {
            User u = userList.get(i);
            System.out.println(u.getUsername());
        }



    }
}
