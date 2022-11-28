package com.fict.workinggroups.chess_puzzles.controller.restcontroller;

import com.fict.workinggroups.chess_puzzles.entity.Fen;
import com.fict.workinggroups.chess_puzzles.repository.FenRepository;
import com.fict.workinggroups.chess_puzzles.service.FenService;
import com.fict.workinggroups.chess_puzzles.exception.FenNotFound;
import com.fict.workinggroups.chess_puzzles.exception.InvalidFenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//localhost:55505/api/fens/ - to get all fens GET
//localhost:55505/api/fens/add - to add new POST
//localhost:55505/api/fens/{id} - to get specific fen GET
//localhost:55505/api/fens/edit/{id} - to edit one fen PUT
//localhost:55505/api/fens/delete/{id} - to delete one fen DELETE
@RequestMapping("/api/fens")
@RestController
public class FenRestController {
    @Autowired
    private FenRepository fenRepository;
    @Autowired
    private FenService fenService;

    @GetMapping
    public List<Fen> getAllFens() {
        return fenService.getAllFens();
    }

    @PostMapping("/add")
    public ResponseEntity createFen(@ModelAttribute Fen fen) {
        try {
            fenService.saveFen(fen);
            return ResponseEntity.ok().body(fen);
        } catch (InvalidFenException e) {
            return ResponseEntity.status(422).body(e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getFenById(@PathVariable String id)  {
        {
            try {
                fenService.getFenById(id);

                return ResponseEntity.ok().body(id);
            }
            catch (FenNotFound e)
            {
                return ResponseEntity.status(422).body(e.getMessage());
            }
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity editFen(@PathVariable String id, @ModelAttribute Fen fenDetails)  {
       try {
           fenService.edit(id,fenDetails.getFen(),fenDetails.getDescription());
           return ResponseEntity.ok().body(fenDetails);
       }
       catch (InvalidFenException e)
       {
           return ResponseEntity.status(422).body(e.getMessage());

       }
    }

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

