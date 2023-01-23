package com.fict.workinggroups.chess_puzzles.web.controller;

import com.fict.workinggroups.chess_puzzles.exception.InvalidTournament;
import com.fict.workinggroups.chess_puzzles.model.entity.Tournament;
import com.fict.workinggroups.chess_puzzles.service.TournamentService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


@AllArgsConstructor
@Controller
public class TournamentController {


    private TournamentService tournamentService;

    @GetMapping("/viewTournaments")
    public String viewTournaments(Model model) {
        model.addAttribute("tournaments", tournamentService.getAllTournaments());
        return "tournament_list";

    }


    @GetMapping("/addTournament")
    public String addTournament(Model model, @RequestParam(required = false) String error) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        Tournament tournament = new Tournament();
        model.addAttribute("tournament", tournament);
        return "add_tournament";
    }

    @PutMapping("/editTournament/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editTournament(@PathVariable(value = "id") String id, Model model) {
        Tournament tournament = this.tournamentService.getTournamentById(id).get();
        //this.tournamentService.edit(id,tournament.getName());
        model.addAttribute("tournament", tournament);
        return "edit_tournament";


    }

    @PostMapping("/saveTournament")
    public String saveTournament(@ModelAttribute("tournament") Tournament tournament, @RequestParam(required = false) String id, Model model) throws ParseException {
//        //Create a DateTimeFormatter with your required format:
//        SimpleDateFormat dateParser = new SimpleDateFormat ("dd/MM/yyyy"); //Format for input
//        String date= String.valueOf(tournamentDto.getDate());
//        Date date1=dateParser.parse(date);
//       // SimpleDateFormat dateFormatter = new SimpleDateFormat ("dd-MM-yyyy"); //Format for output
//        tournamentDto.setDate(date1);
        try {
            if (id != null) {
                this.tournamentService.edit(id, tournament);
                model.addAttribute("tournament", tournament);

            } else {
                this.tournamentService.save(tournament);

            }
            return "redirect:/viewTournaments";

        } catch (InvalidTournament e) {

            return "redirect:/addTournament?error=" + e.getMessage();
        }
    }


    @DeleteMapping("/deleteTournament/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteTournament(@PathVariable(value = "id") String id) {
        this.tournamentService.deleteTournament(id);
        return "redirect:/viewTournaments";
    }


//    @PostMapping("/tournamentDetails/{id}")
//    public String tournamentDetails(@PathVariable(value = "id") String id) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User) authentication.getPrincipal();
//        this.tournamentService.joinTournament(id, user);
//
//        return "redirect:/tournamentPlayers/{id}";
//
//    }

    @GetMapping("/tournamentDetails/{id}")
    public String showPlayer(Model model, @PathVariable("id") String id) {
        model.addAttribute("players", tournamentService.listPlayersInTournament(id));
        //model.addAttribute("fenList", tournamentService.listFensInTournament(id));
        return "tournament_players";

    }

}


