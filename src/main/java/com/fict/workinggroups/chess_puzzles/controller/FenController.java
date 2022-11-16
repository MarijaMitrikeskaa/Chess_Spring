package com.fict.workinggroups.chess_puzzles.controller;

import com.fict.workinggroups.chess_puzzles.entity.FenModel;
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
        FenModel fenModel = new FenModel();
        model.addAttribute("fenModel", fenModel);
        return "new_fen";
    }

    @PostMapping("/saveFen")
    public String saveFen(@ModelAttribute("fenModel") FenModel fenModel, Model model)  {

        try{
            fenService.saveFen(fenModel);
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
        FenModel fenModel = fenService.findById(id).get();
              model.addAttribute("fen", fenModel);
              return "update_fen";
           }


    @DeleteMapping("/deleteFenID/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteFenID(@PathVariable(value = "id") String id) {
        this.fenService.deleteFen(id);
        return "redirect:/viewFens";
    }
}
