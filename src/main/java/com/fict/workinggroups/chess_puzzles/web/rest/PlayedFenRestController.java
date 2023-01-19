package com.fict.workinggroups.chess_puzzles.web.rest;


import com.fict.workinggroups.chess_puzzles.exception.FenNotFound;
import com.fict.workinggroups.chess_puzzles.exception.WrongFenSolutionException;
import com.fict.workinggroups.chess_puzzles.model.dto.PlayedFenDto;
import com.fict.workinggroups.chess_puzzles.model.entity.PlayedFen;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;
import com.fict.workinggroups.chess_puzzles.service.PlayedFensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.HashMap;

@RestController
@RequestMapping("/api/playedFen")
public class PlayedFenRestController {

    @Autowired
    private PlayedFensService playedFensService;

    @Autowired
    private EntityManager entityManager;


    @PostMapping("/checkSolution")
    public ResponseEntity checkFenSolution(PlayedFenDto playedFenDto) {
        try {
            playedFensService.checkSolution(playedFenDto);
            return ResponseEntity.ok().body(playedFenDto);
        } catch (WrongFenSolutionException e) {
            return ResponseEntity.status(422).body(e.getMessage());
        }
    }

    @Query(value = "SELECT * FROM Lead")
    @PostMapping("/leaderboard")
    public ResponseEntity leaderboard(PlayedFenDto playedFenDto, Player player, PlayedFen playedFen, @Param("username") String username, @Param("sum") int sum) {
        try {
            this.playedFensService.playerPoints(playedFenDto, player, playedFen);
            HashMap<String, Integer> lead = new HashMap<>();
            lead.put(username, sum);

            return ResponseEntity.ok().body(lead);
        } catch (WrongFenSolutionException e) {
            return ResponseEntity.status(422).body(e.getMessage());
        }

    }

//
//    create materialized view "Lead" as
//    SELECT  USERNAME , SUM(PLAYER_POINTS) FROM PLAYED_FEN, PLAYER
//    WHERE PLAYED_FEN.PLAYER_ID=PLAYER.ID
//    GROUP BY PLAYER_ID, TOURNAMENT_ID, USERNAME
//    ORDER BY sum(player_points) desc

}