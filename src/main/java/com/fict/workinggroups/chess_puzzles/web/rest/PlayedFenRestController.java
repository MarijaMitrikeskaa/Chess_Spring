package com.fict.workinggroups.chess_puzzles.web.rest;


import com.fict.workinggroups.chess_puzzles.exception.FenNotFound;
import com.fict.workinggroups.chess_puzzles.model.dto.PlayedFenDto;
import com.fict.workinggroups.chess_puzzles.service.PlayedFensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/playedFen")
public class PlayedFenRestController {

    @Autowired
    private PlayedFensService playedFensService;

//    @PostMapping("/checkSolution")
////    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
//    public Map<String, Boolean> checkFenSolution(@RequestParam Map<String, String> body, PlayedFenDto playedFenDto)
//    {
//        try {
//            playedFensService.checkSolution(playedFenDto);
//            return Collections.singletonMap("success", true);
//        } catch (FenNotFound e) {
//            return (Map<String, Boolean>) ResponseEntity.status(422).body(e.getMessage());
//        }
//    }

    @PostMapping("/checkSolution")
    public ResponseEntity checkFenSolution(PlayedFenDto playedFenDto)
    {
        try {
            playedFensService.checkSolution(playedFenDto);
            return ResponseEntity.ok().body(playedFenDto);
        } catch (FenNotFound e) {
            return ResponseEntity.status(422).body(e.getMessage());
        }
    }


}