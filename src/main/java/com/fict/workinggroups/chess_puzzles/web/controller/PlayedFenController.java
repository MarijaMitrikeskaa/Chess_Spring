package com.fict.workinggroups.chess_puzzles.web.controller;

import com.fict.workinggroups.chess_puzzles.service.PlayedFensService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class PlayedFenController {


    private PlayedFensService playedFensService;

    @GetMapping("/viewPlayedFen")
    public String viewPlayers(Model model, String id) {
        model.addAttribute("playedFen", playedFensService.getPlayerFenById(id));
        return "playedFen";

    }

//    @DeleteMapping("/deletePlayedFen/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public String deletePlayedFen(@PathVariable(value = "id") String id) {
//        this.playedFensService.deletePlayedFen(id);
//        return "redirect:/viewPlayers";
//    }
}



