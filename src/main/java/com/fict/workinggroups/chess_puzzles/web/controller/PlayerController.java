package com.fict.workinggroups.chess_puzzles.web.controller;

import com.fict.workinggroups.chess_puzzles.exception.InvalidFenException;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;
import com.fict.workinggroups.chess_puzzles.model.entity.Tournament;
import com.fict.workinggroups.chess_puzzles.service.PlayerService;
import com.fict.workinggroups.chess_puzzles.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlayerController {


    @Autowired
     private PlayerService playerService;
    private TournamentService tournamentService;

    @GetMapping("/viewPlayers")
    public String viewPlayers(Model model){
        model.addAttribute("players",playerService.getAllPlayers());
        return "list_players";

    }



    @PutMapping("/editPlayer/{id}")

    public String editPlayer(@PathVariable(value = "id") String id, Model model)  {

     Player player=playerService.getPlayerById(id).get();
     model.addAttribute("player",player);
     return "edit_player";



    }

    @DeleteMapping("/deletePlayer/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deletePlayer(@PathVariable(value = "id") String id) {
        this.playerService.deletePlayer(id);
        return "redirect:/viewPlayers";
    }

//    @PostMapping("/joinTournament/{id}")
//    public String joinTournament(@PathVariable(value = "id") String id,Model model,Player player) {
//        Tournament tournament = this.tournamentService.getTournamentById(id).get();
//        String playerId=player.getId();
//        model.addAttribute("tournament", tournament);
//        model.addAttribute("playerId",playerId);
//        return "redirect:/viewTournaments";
//
//    }
}
// TODO: 09.12.2022 Player profile so edit kopce



