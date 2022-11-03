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
    public List<FenModel> getAllFens() {
        return fenRepository.findAll();
    }

    @PostMapping("/add")
    public FenModel createFen(@ModelAttribute @Valid FenModel fenModel) throws InvalidFenException {


      if(fenService.isValidFen(fenModel.getFen())) {
          return fenRepository.save(fenModel);
      }
      else throw  new InvalidFenException();


    }

    @GetMapping("/{id}")
    public ResponseEntity<FenModel> getFenById(@PathVariable String id) throws ResourceNotFoundException {
        {
            FenModel fenModel = fenRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This FEN %id is not found", id ));
            return ResponseEntity.ok().body(fenModel);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<FenModel> updateFen(@PathVariable String id, @ModelAttribute FenModel fenDetails) throws ResourceNotFoundException {


                FenModel fenModel = fenRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This FEN %id is not found" , id) );
        fenModel.setFen(fenDetails.getFen());
        fenModel.setDescription(fenDetails.getDescription());
        fenRepository.save(fenModel);
        return ResponseEntity.ok().body(fenModel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteFen(@PathVariable String id)  {
        fenRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

//todo
//treba opcija da moze, od front-end da se odgovori
//----> "A1" - "B4" za FEN_ID "SDKGDSJFLHDSKFHLS", napraveno od X account
//from, to, fenId,accountId (date, time....)