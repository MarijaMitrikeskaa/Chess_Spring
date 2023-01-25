package com.fict.workinggroups.chess_puzzles.web.rest;

import com.fict.workinggroups.chess_puzzles.exception.InvalidTournament;
import com.fict.workinggroups.chess_puzzles.exception.TournamentNotFound;
import com.fict.workinggroups.chess_puzzles.model.dto.FenDto;
import com.fict.workinggroups.chess_puzzles.model.dto.TournamentDto;
import com.fict.workinggroups.chess_puzzles.model.entity.Fen;
import com.fict.workinggroups.chess_puzzles.model.entity.Tournament;
import com.fict.workinggroups.chess_puzzles.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequestMapping("/api/tournament")
@RestController
public class TournamentRestController {
    @Autowired
    private TournamentService tournamentService;


    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable String id) {
        try {
            Optional<Tournament> tournament = tournamentService.getTournamentById(id);
            return tournament.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.status(422).body(null));
        } catch (TournamentNotFound e) {
            return ResponseEntity.status(422).body(null);
        }
    }

    @GetMapping("/all")
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @PostMapping("/add")
    public ResponseEntity saveTournament(@ModelAttribute TournamentDto tournamentDto) {
        try {
            tournamentService.saveTournament(tournamentDto);
            return ResponseEntity.ok().body(tournamentDto);
        } catch (InvalidTournament e) {
            return ResponseEntity.status(422).body(e.getMessage());
        }
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTournament(@PathVariable String id) {
        try {
            tournamentService.deleteTournament(id);
            return ResponseEntity.ok().body(id);
        } catch (TournamentNotFound e) {
            return ResponseEntity.status(422).body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Tournament> editTournament(@PathVariable String id, @ModelAttribute TournamentDto tournamentDto) {
        return this.tournamentService.edit(id, tournamentDto)
                .map(tournament -> ResponseEntity.ok().body(tournament))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @GetMapping("/listFensInTournament/{id}")
    public Set<FenDto> listFensInTournament(@PathVariable String id) {
        return this.tournamentService.listFensInTournament(id);
    }

    @GetMapping("/listFensByTournamentName/{name}")
    public Set<FenDto> listFensByTournamentName(@PathVariable String name) {
        return this.tournamentService.listFensByTournamentName(name);
    }
}
