package com.fict.workinggroups.chess_puzzles.web.controller;

import com.fict.workinggroups.chess_puzzles.exception.InvalidTournament;
import com.fict.workinggroups.chess_puzzles.model.entity.Tournament;
import com.fict.workinggroups.chess_puzzles.model.entity.User;
import com.fict.workinggroups.chess_puzzles.service.PlayerService;
import com.fict.workinggroups.chess_puzzles.service.TournamentService;
import com.fict.workinggroups.chess_puzzles.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class TournamentController {


    private TournamentService tournamentService;
    private PlayerService playerService;
    private UserService userService;

    @GetMapping("/viewTournaments")
    public String viewTournaments(Model model) {
        model.addAttribute("tournaments", tournamentService.getAllTournaments());
        return "tournaments_list";

    }


    @GetMapping("/addTournament")
    public String addTournament(Model model) {
        Tournament tournament = new Tournament();
        model.addAttribute("tournament", tournament);
        return "add_tournament";
    }

    @PutMapping("/editTournament/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editTournament(@PathVariable(value = "id") String id, Model model) {
        Tournament tournament = this.tournamentService.getTournamentById(id).get();
        model.addAttribute("tournament", tournament);
        return "edit_tournament";


    }

    @PostMapping("/saveTournament")
    public String saveTournament(@ModelAttribute("tournament") Tournament tournament, Model model) {

        try {
            this.tournamentService.saveTournament(tournament);
            return "redirect:/viewTournaments";

        } catch (InvalidTournament e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            return "add_tournament";
        }
    }

//    @PostMapping
//    public  String saveTournament(@RequestParam Tournament name){
//        try{
//            this.tournamentService.saveTournament(name);
//            return "redirect:/viewTournaments";
//        } catch (InvalidTournament exception){
//            return "add_tournament" + exception.getMessage();
//        }
//    }

    @DeleteMapping("/deleteTournament/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteTournament(@PathVariable(value = "id") String id) {
        this.tournamentService.deleteTournament(id);
        return "redirect:/viewTournaments";
    }
//
    @PostMapping("/joinTournament/{id}")
    public String joinTournament(@PathVariable(value = "id")String id, Model model, HttpServletRequest request) {
        Tournament tournament = this.tournamentService.getTournamentById(String.valueOf(id)).get();
        String username=request.getRemoteUser();
        Optional<User> user=this.userService.findUserByUsername(username);

        this.tournamentService.joinTournament(tournament.getId(),user.get().getId());
        model.addAttribute("tournament", tournament);

        return "redirect:/viewTournaments";

    }

}
