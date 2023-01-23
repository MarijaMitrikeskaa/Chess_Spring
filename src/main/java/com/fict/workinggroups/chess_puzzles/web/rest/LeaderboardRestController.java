package com.fict.workinggroups.chess_puzzles.web.rest;


import com.fict.workinggroups.chess_puzzles.exception.LeaderBoardNotFoundException;
import com.fict.workinggroups.chess_puzzles.model.entity.Leaderboard;
import com.fict.workinggroups.chess_puzzles.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardRestController {


    @Autowired
    LeaderboardService leaderboardService;


    // /list/{tournamentId}
    //findLeaderboardEntityByTournamentId(String tournamentId)

    @GetMapping("/list/{tournamentId}") //todo, big todo, dto, repository, service everything
    public ResponseEntity getLeaderboard(@PathVariable String tournamentId) {
        {
            try {
                Set<Leaderboard> leaderboard = this.leaderboardService.getLeaderboardByTournamentId(tournamentId);
                if (!leaderboard.isEmpty()) {
                    return ResponseEntity.ok().body(leaderboard);
                } else {
                    return ResponseEntity.status(422).body(null);
                }
            } catch (LeaderBoardNotFoundException e) {
                return ResponseEntity.status(422).body(null);
            }
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