package com.fict.workinggroups.chess_puzzles.Controllers;

import com.fict.workinggroups.chess_puzzles.Entity.Fen;
import com.fict.workinggroups.chess_puzzles.Service.FenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FenController {
    @Autowired
    private FenService fenSrevice;

    // pokazuva lista na FENS
    @GetMapping("/")
    public String viewHomePage (Model model){
        model.addAttribute("FenList", fenSrevice.getAllFens());
        return "Fen";
    }

    @GetMapping("/newFenForm")
    public String newFenForm(Model model){
        Fen fen = new Fen();
        model.addAttribute("fen", fen);
        return "New_Fen";
    }
    @PostMapping("/saveFen")
    public String saveFen (@ModelAttribute("fen") Fen fen) {
        fenSrevice.saveFen(fen);
        return "redirect:/";
    }

    @GetMapping("/UpdateForm/{Id}")
    public String UpdateForm(@PathVariable(value = "Id") long Id, Model model){
        Fen fen = fenSrevice.getFenById(Id);
        model.addAttribute("fen", fen);
        return "Update_Fen";
    }
    @GetMapping("/deleteFenID/{Id}")
    public String deleteFenID(@PathVariable(value = "Id") long Id){
        this.fenSrevice.deleteFen(Id);
        return "redirect:/";
    }
}
