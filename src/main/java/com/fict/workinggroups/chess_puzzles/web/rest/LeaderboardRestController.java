package com.fict.workinggroups.chess_puzzles.web.rest;


import com.fict.workinggroups.chess_puzzles.exception.WrongFenSolutionException;
import com.fict.workinggroups.chess_puzzles.model.dto.PlayedFenDto;
import com.fict.workinggroups.chess_puzzles.service.PlayedFensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardRestController {

    @Autowired
    private PlayedFensService playedFensService;

    @Autowired
    private EntityManager entityManager;


    // /list/{tournamentId}
    //findLeaderboardEntityByTournamentId(String tournamentId)

    @GetMapping("/list") //todo, big todo, dto, repository, service everything
    public ResponseEntity getLeaderboard(PlayedFenDto playedFenDto) {
        try {
            playedFensService.checkSolution(playedFenDto);
            return ResponseEntity.ok().body(playedFenDto);
        } catch (WrongFenSolutionException e) {
            return ResponseEntity.status(422).body(e.getMessage());
        }
    }

    //Nickname, totalPoints

    //for version 2, or if you have more time
    //kolku puzzles probal da gi resi - numberOfPlayedPuzzles 733
    //numberOfCorrectPlayedPuzzles 677 tocni od 733 so gi igral
    //kolku gresni ne cuvame kako podatok, mozi so odzemanje da se najdi

    //Petko 1200 points, 677 correct out of 733
    //Petko 1300 points, 600 correct out of 1000


    //updating the leaderboard is done via PlayedFenService
}