package com.example.controller;

import com.example.service.AuthenticationFailureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final AuthenticationFailureService authenticationFailureService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        var blockedUsers = authenticationFailureService.getBlockedUsers();
        model.addAttribute("blockedUsers", blockedUsers);
        return "admin";
    }

}
