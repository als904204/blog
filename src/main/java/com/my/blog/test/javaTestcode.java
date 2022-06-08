package com.my.blog.test;

import com.my.blog.Entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class javaTestcode {
    public static void main(String[] args) {
        try {
            Robot rb = new Robot();
            rb.mouseMove(1405, 6224);
            rb.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(500);
            rb.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}


