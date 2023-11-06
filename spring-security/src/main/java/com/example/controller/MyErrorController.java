package com.example.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletResponse response, Model model) {
        HttpStatus httpStatus = HttpStatus.valueOf(response.getStatus());
        model.addAttribute("status", httpStatus.value());
        model.addAttribute("reason", httpStatus.getReasonPhrase());
        return "error";
    }

}
