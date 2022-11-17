package com.fict.workinggroups.chess_puzzles.controller;

import com.fict.workinggroups.chess_puzzles.entity.User;
import com.fict.workinggroups.chess_puzzles.exception.InvalidUserCredentialsException;

import com.fict.workinggroups.chess_puzzles.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {





    @GetMapping
    public String getLoginPage() {
        //model.addAttribute("bodyContent","login");
        return "login";
    }

//    @PostMapping
//    public String login(HttpServletRequest request, Model model) {
//        User user = null;
//        try{
//            user = this.authService.login(request.getParameter("username"),
//                    request.getParameter("password"));
//            request.getSession().setAttribute("user", user);
//            return "redirect:/homepage";
//        }
//        catch (InvalidUserCredentialsException exception) {
//            model.addAttribute("hasError", true);
//            model.addAttribute("error", exception.getMessage());
//            return "login";
//        }
//    }
}

