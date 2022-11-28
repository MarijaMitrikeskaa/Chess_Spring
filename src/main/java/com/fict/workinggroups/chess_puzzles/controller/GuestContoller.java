package com.fict.workinggroups.chess_puzzles.controller;

import com.fict.workinggroups.chess_puzzles.entity.User;
import com.fict.workinggroups.chess_puzzles.exception.InvalidFenException;

import com.fict.workinggroups.chess_puzzles.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GuestContoller {
    @Autowired
   private UserService userService;

    @GetMapping("/guestPage")
    public String viewHomePage(Model model) {
        User user=new User();
        model.addAttribute("Guest", user);

        return "guestpage";
    }

    @GetMapping("/guestGame")
    public String viewGuestGame(Model model,String id){
        model.addAttribute("Guest", userService.getGuest(id));


        return "GuestGame";
    }

    @PostMapping("/saveGuest")
    public String saveGuest(@ModelAttribute("User") User guest, Model model)  {

        try{
           userService.saveGuest(guest);
            return "home";

        }
        catch (InvalidFenException e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            return "guestpage";
        }



    }
}
