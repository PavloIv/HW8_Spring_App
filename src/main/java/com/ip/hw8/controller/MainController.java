package com.ip.hw8.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/main")
    public String main() {
        return "index";
    }

    @GetMapping("/registrationNewUser")
    public String createForm() {
        return "user/createUser";
    }
}

