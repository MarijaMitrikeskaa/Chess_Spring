package com.fict.workinggroups.chess_puzzles.web.controller;

import com.fict.workinggroups.chess_puzzles.model.entity.Fen;
import com.fict.workinggroups.chess_puzzles.service.FenService;
import com.fict.workinggroups.chess_puzzles.exception.InvalidFenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FenController {
    @Autowired
    private FenService fenService;

    @GetMapping("/viewFens")
    public String viewHomePage(Model model) {
        model.addAttribute("fenList", fenService.getAllFens());
        return "fen_list";
    }

    @GetMapping("/newFenForm")
    public String newFenForm(Model model) {
        Fen fen = new Fen();
        model.addAttribute("fen", fen);
        return "new_fen";
    }

    @PostMapping("/saveFen")
    public String saveFen(@ModelAttribute("fen") Fen fen, Model model)  {

        try{
            fenService.saveFen(fen);
            return "redirect:/viewFens";

        }
        catch (InvalidFenException e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            return "new_fen";
        }



    }


    @PutMapping("/updateForm/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String UpdateForm(@PathVariable(value = "id") String id, Model model){
        Fen fen = fenService.findById(id).get();
              model.addAttribute("fen", fen);
              return "update_fen";
    }


    @DeleteMapping("/deleteFenID/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteFenId(@PathVariable(value = "id") String id) {
        this.fenService.deleteFen(id);
        return "redirect:/viewFens";
    }
}
