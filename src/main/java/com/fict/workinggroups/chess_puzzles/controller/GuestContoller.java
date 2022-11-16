package com.fict.workinggroups.chess_puzzles.controller;

import com.fict.workinggroups.chess_puzzles.entity.FenModel;
import com.fict.workinggroups.chess_puzzles.entity.Guest;
import com.fict.workinggroups.chess_puzzles.exception.InvalidFenException;
import com.fict.workinggroups.chess_puzzles.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GuestContoller {
    @Autowired
   private GuestService guestService;

    @GetMapping("/guestPage")
    public String viewHomePage(Model model) {
        Guest guest = new Guest();
        model.addAttribute("Guest", guest);

        return "guestpage";
    }

    @GetMapping("/Guestgame")
    public String viewGuestGame(){

        return "GuestGame";
    }

    @PostMapping("/saveGuestname")
    public String saveFen(@ModelAttribute("Guest") Guest guest, Model model)  {

        try{
            guestService.saveGuest(guest);
            return "redirect:/GuestGame";

        }
        catch (InvalidFenException e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            return "guestpage";
        }



    }
}
