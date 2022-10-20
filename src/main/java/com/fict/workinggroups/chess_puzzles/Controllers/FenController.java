package com.fict.workinggroups.chess_puzzles.Controllers;

import com.fict.workinggroups.chess_puzzles.Entity.FenModel;
import com.fict.workinggroups.chess_puzzles.Service.FenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FenController {
    @Autowired
    private FenService fenService;

    // pokazuva lista na FENS
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("fenList", fenService.getAllFens());
        return "fen_list";
    }

    @GetMapping("/newFenForm")
    public String newFenForm(Model model) {
        FenModel fenModel = new FenModel();
        model.addAttribute("fenModel", fenModel);
        return "new_fen";
    }

    @PostMapping("/saveFen")
    public String saveFen(@ModelAttribute("fenModel") FenModel fenModel) {
        fenService.saveFen(fenModel);
        return "redirect:/";
    }

    @GetMapping("/updateForm/{id}") //putMapping
    public String UpdateForm(@PathVariable(value = "id") long id, Model model) {
        FenModel fenModel = fenService.getFenById(id);
        model.addAttribute("fenModel", fenModel);
        return "update_fen";
    }

    @GetMapping("/deleteFenID/{id}")
    //@DeleteMapping("/deleteFenID/{id}") todo
    public String deleteFenID(@PathVariable(value = "id") long id) {
        this.fenService.deleteFen(id);
        return "redirect:/";
    }
}
