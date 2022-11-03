package com.fict.workinggroups.chess_puzzles.Controllers;

import com.fict.workinggroups.chess_puzzles.Entity.FenModel;
import com.fict.workinggroups.chess_puzzles.Service.FenService;
import com.fict.workinggroups.chess_puzzles.exception.InvalidFenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class FenController {
    @Autowired
    private FenService fenService;

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
    public String saveFen(@ModelAttribute("fenModel") FenModel fenModel, Model model) {

        try{
            fenService.isValidFen(fenModel.getFen());
            fenService.saveFen(fenModel);
            return "redirect:/";

        }
        catch (InvalidFenException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "redirect:/fenModel?error=FenModelNotValid";
        }

    }


    @PutMapping("/updateForm/{id}")
  public String UpdateForm(@PathVariable(value = "id") String id, Model model){
        FenModel fenModel = fenService.findById(id).get();
              model.addAttribute("fen", fenModel);
              return "update_fen";
           }


    @DeleteMapping("/deleteFenID/{id}")
    public String deleteFenID(@PathVariable(value = "id") String id) {
        this.fenService.deleteFen(id);
        return "redirect:/";
    }
}
