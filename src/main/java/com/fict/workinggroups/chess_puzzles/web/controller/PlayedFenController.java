package com.fict.workinggroups.chess_puzzles.web.controller;

import com.fict.workinggroups.chess_puzzles.exception.PlayerNotFound;
import com.fict.workinggroups.chess_puzzles.model.entity.PlayedFen;
import com.fict.workinggroups.chess_puzzles.service.PlayedFensService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class PlayedFenController {


    private PlayedFensService playedFensService;

    @GetMapping("/viewPlayedFen")
    public String viewPlayedFens(Model model) {
        PlayedFen
        model.addAttribute("playedFen",playedFensService);
        return "playedFen";
    }

    @PostMapping("/savePlayedFen")
    public String savePlayedFen(@ModelAttribute("playedfen")PlayedFen playedFen, Model model){
        try {
            playedFensService.savePlayedFen(playedFen);
            return "redirect:viewPlayedFen";
        }
        catch (PlayerNotFound e){
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            return "add_playedfen";
        }

    }



//    @DeleteMapping("/deletePlayedFen/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public String deletePlayedFen(@PathVariable(value = "id") String id) {
//        this.playedFensService.deletePlayedFen(id);
//        return "redirect:/viewPlayers";
//    }
}



