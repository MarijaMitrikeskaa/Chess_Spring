package com.fict.workinggroups.chess_puzzles.Controllers;

import com.fict.workinggroups.chess_puzzles.Entity.FenModel;
import com.fict.workinggroups.chess_puzzles.Repository.FenRepository;
import com.fict.workinggroups.chess_puzzles.Service.FenService;
import com.fict.workinggroups.chess_puzzles.exception.InvalidFenException;
import com.fict.workinggroups.chess_puzzles.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<FenModel> getAllFens() {
        return fenService.getAllFens();
    }

    @PostMapping("/add")
    public ResponseEntity createFen(@ModelAttribute FenModel fenModel) {
        try {
            fenService.saveFen(fenModel);
            return ResponseEntity.ok().body(fenModel);
        } catch (Exception e) {
            return ResponseEntity.status(422).body("Invalid Fen");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<FenModel> getFenById(@PathVariable String id) throws ResourceNotFoundException {
        {
            FenModel fenModel = fenService.findById(id).orElseThrow(() -> new ResourceNotFoundException("This FEN %id is not found", id));
            return ResponseEntity.ok().body(fenModel);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity updateFen(@PathVariable String id, @ModelAttribute FenModel fenDetails) throws ResourceNotFoundException {
       try {
           fenService.edit(id,fenDetails.getFen(),fenDetails.getDescription());
           return ResponseEntity.ok().body(fenDetails);
       }
       catch (Exception e)
       {
           return ResponseEntity.status(422).body("Fen Not Found");

       }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteFen(@PathVariable String id) {
        try {
            fenService.deleteFen(id);
            return ResponseEntity.ok().body(id);
        } catch (Exception e) {

            return ResponseEntity.status(422).body("Fen Not Found");
        }
    }
}

