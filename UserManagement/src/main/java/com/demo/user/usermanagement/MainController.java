package com.demo.user.usermanagement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String homePage() {
        System.out.println("Main Controller");
        return "index";
    }
}
