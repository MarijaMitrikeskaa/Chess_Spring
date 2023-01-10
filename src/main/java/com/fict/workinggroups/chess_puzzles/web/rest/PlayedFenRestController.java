package com.fict.workinggroups.chess_puzzles.web.rest;


import com.fict.workinggroups.chess_puzzles.model.dto.PlayedFenDto;
import com.fict.workinggroups.chess_puzzles.service.PlayedFensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/playedFen")
public class PlayedFenRestController {

    @Autowired
    private PlayedFensService playedFensService;

    @PostMapping("/checkSolution")
    public boolean checkFenSolution(@RequestBody PlayedFenDto playedFenDto) {

        return this.playedFensService.checkSolution(playedFenDto);



    }

}