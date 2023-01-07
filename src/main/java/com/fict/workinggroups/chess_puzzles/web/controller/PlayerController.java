package com.fict.workinggroups.chess_puzzles.web.controller;

import com.fict.workinggroups.chess_puzzles.exception.InvalidUsernameException;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;
import com.fict.workinggroups.chess_puzzles.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlayerController {


    @Autowired
    private PlayerService playerService;

    @GetMapping("/viewPlayers")
    public String viewPlayers(Model model) {
        model.addAttribute("players", playerService.getAllPlayers());
        return "player_list";

    }

    @PostMapping("/savePlayer")
    public String savePlayer(Player player, @RequestParam String id) {
        if (id != null) {
            this.playerService.editPlayer(id, player.getUsername());
        } else {
            throw new InvalidUsernameException();
        }


        return "redirect:/viewPlayers";


    }




    @PutMapping("/editPlayer/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or @playerServiceImpl.hasId(#id)")
    public String editPlayer(@PathVariable(value = "id") String id, Model model) {

        Player player = playerService.getPlayerById(id).get();
        model.addAttribute("player", player);
        return "edit_player";


    }

    @DeleteMapping("/deletePlayer/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deletePlayer(@PathVariable(value = "id") String id) {
        this.playerService.deletePlayer(id);
        return "redirect:/viewPlayers";
    }


}




