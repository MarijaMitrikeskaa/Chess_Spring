package com.fict.workinggroups.chess_puzzles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeContoller {
    @GetMapping("/homepage")
    public String viewHomePage() {
        return "home";
    }
}
