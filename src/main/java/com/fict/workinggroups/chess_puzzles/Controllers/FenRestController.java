package com.fict.workinggroups.chess_puzzles.Controllers;

import com.fict.workinggroups.chess_puzzles.Entity.FenModel;
import com.fict.workinggroups.chess_puzzles.Repository.FenRepository;
import com.fict.workinggroups.chess_puzzles.exception.ResourceNotFoundException;
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

    @GetMapping
    public List<FenModel> getAllFens() {
        return fenRepository.findAll();
    }

    @PostMapping("/add")
    public FenModel createFen(@ModelAttribute FenModel fenModel) {
        return fenRepository.save(fenModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FenModel> getFenById(@PathVariable long id) throws ResourceNotFoundException {
        {
            FenModel fenModel = fenRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This FEN is not found"));
            return ResponseEntity.ok().body(fenModel);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<FenModel> updateFen(@PathVariable long id, @ModelAttribute FenModel fenDetails) throws ResourceNotFoundException {
        FenModel fenModel = fenRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This FEN is not found"));
        fenModel.setFen(fenDetails.getFen());
        fenModel.setDescription(fenDetails.getDescription());
        fenRepository.save(fenModel);
        return ResponseEntity.ok().body(fenModel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteFen(@PathVariable long id) throws ResourceNotFoundException {
        fenRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

//todo
//treba opcija da moze, od front-end da se odgovori
//----> "A1" - "B4" za FEN_ID "SDKGDSJFLHDSKFHLS", napraveno od X account
//from, to, fenId,accountId (date, time....)