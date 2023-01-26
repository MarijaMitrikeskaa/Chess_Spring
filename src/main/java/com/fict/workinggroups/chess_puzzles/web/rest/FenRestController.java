package com.fict.workinggroups.chess_puzzles.web.rest;

import com.fict.workinggroups.chess_puzzles.exception.FenNotFound;
import com.fict.workinggroups.chess_puzzles.model.dto.FenSolutionDto;
import com.fict.workinggroups.chess_puzzles.model.entity.Fen;
import com.fict.workinggroups.chess_puzzles.service.FenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/fens")
@RestController
public class FenRestController {

    @Autowired
    private FenService fenService;

    @GetMapping
    public List<Fen> getAllFens() {


        return fenService.getAllFens();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fen> getFenById(@PathVariable String id) {
        {
            try {
                Optional<Fen> fen = fenService.getFenById(id);
                if (!fen.isEmpty()) {
                    return ResponseEntity.ok().body(fen.get());
                } else {
                    return ResponseEntity.status(422).body(null);
                }
            } catch (FenNotFound e) {
                return ResponseEntity.status(422).body(null);
            }
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Fen> saveFen(@ModelAttribute FenSolutionDto fenSolutionDto) {
        return this.fenService.save(fenSolutionDto)
                .map(fen -> ResponseEntity.ok().body(fen))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Fen> editFen(@PathVariable String id, @ModelAttribute FenSolutionDto fenSolutionDto) {
        return this.fenService.edit(id, fenSolutionDto)
                .map(fen -> ResponseEntity.ok().body(fen))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/addSolution/{id}")
    public ResponseEntity<Fen> addFenSolution(@PathVariable String id, String solution) {


        return this.fenService.addFenSolution(id, solution)
                .map(fen -> ResponseEntity.ok().body(fen))
                .orElseGet(() -> ResponseEntity.badRequest().build());


    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteFen(@PathVariable String id) {
        try {
            fenService.deleteFen(id);
            return ResponseEntity.ok().body(id);
        } catch (FenNotFound e) {

            return ResponseEntity.status(422).body(e.getMessage());
        }
    }
}

