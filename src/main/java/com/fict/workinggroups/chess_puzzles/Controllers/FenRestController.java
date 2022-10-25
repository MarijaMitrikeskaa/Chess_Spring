package com.fict.workinggroups.chess_puzzles.Controllers;

import com.fict.workinggroups.chess_puzzles.Entity.FenModel;
import com.fict.workinggroups.chess_puzzles.Repository.FenRepository;
import com.fict.workinggroups.chess_puzzles.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api")
@RestController
public class FenRestController {
@Autowired
    private FenRepository fenRepository;
// site fens
@GetMapping("/fens")
    public List<FenModel> getAllFens(){
    return fenRepository.findAll();
}
// kreiranje nov fen
@PostMapping("/add")
public FenModel createFen(@RequestBody FenModel fenModel){
    return fenRepository.save(fenModel);
}
//zemi fen spored id
@GetMapping("/fens/{id}")
    public ResponseEntity<FenModel> getFenById(@PathVariable long id) throws ResourceNotFoundException {
    {
        FenModel fenModel = fenRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This FEN is not found"));
        return ResponseEntity.ok().body(fenModel);
    }
}
    @PutMapping("/fens/{id}")
    public ResponseEntity<FenModel> updateFen(@PathVariable long id, @RequestBody FenModel fenDetails)  throws ResourceNotFoundException {
    FenModel fenModel = fenRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This FEN is not found"));
    fenModel.setFen(fenDetails.getFen());
    fenModel.setDescription(fenDetails.getDescription());
    fenRepository.save(fenModel);
    return ResponseEntity.ok().body(fenModel);
    }


    @DeleteMapping("/fens/{id}")
    public ResponseEntity<Object> deleteFen(@PathVariable long id) throws ResourceNotFoundException{
    FenModel fenModel = fenRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This FEN is not found"));
    fenRepository.deleteById(id);
    return ResponseEntity.ok().build();

    }


}

//Create new FEN - POST