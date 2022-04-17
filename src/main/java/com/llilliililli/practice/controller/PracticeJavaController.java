package com.llilliililli.practice.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PracticeJavaController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        model.addAttribute("username", "llilliililli");
        return "greetings" ;// templates/greetings.mustache -> 브라우저 전송
    }
}
