package com.fict.workinggroups.chess_puzzles.web.rest;

import com.fict.workinggroups.chess_puzzles.exception.PlayerNotFound;
import com.fict.workinggroups.chess_puzzles.model.dto.PlayerDto;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;
import com.fict.workinggroups.chess_puzzles.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/player")
@RestController
public class PlayerRestController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getPlayerBid(@PathVariable String id) {
        try {
            playerService.getPlayerById(id);
            return ResponseEntity.ok().body(id);
        } catch (PlayerNotFound e) {
            return ResponseEntity.status(422).body(e.getMessage());
        }
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable String id) {
        try {
            playerService.deletePlayer(id);
            return ResponseEntity.ok().body(id);
        } catch (PlayerNotFound e) {
            return ResponseEntity.status(422).body(e.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Player> editPlayer(@PathVariable String id, @ModelAttribute PlayerDto playerDto) {
        return this.playerService.editPlayer(id, playerDto)
                .map(player -> ResponseEntity.ok().body(player))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
