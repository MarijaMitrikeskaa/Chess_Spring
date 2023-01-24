package com.fict.workinggroups.chess_puzzles.web.rest;


import com.fict.workinggroups.chess_puzzles.exception.WrongFenSolutionException;
import com.fict.workinggroups.chess_puzzles.model.dto.PlayedFenDto;
import com.fict.workinggroups.chess_puzzles.service.PlayedFensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/playedFen")
public class PlayedFenRestController {

    @Autowired
    private PlayedFensService playedFensService;


    @PostMapping("/makeAMove")
    public ResponseEntity makeAMove(PlayedFenDto playedFenDto) {
        try {
            playedFensService.updateLeaderboard(playedFenDto);
            return ResponseEntity.ok().body(playedFenDto);
        } catch (WrongFenSolutionException e) {
            return ResponseEntity.status(422).body(e.getMessage());
        }
    }



}