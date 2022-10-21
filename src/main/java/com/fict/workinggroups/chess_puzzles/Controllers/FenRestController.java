package com.fict.workinggroups.chess_puzzles.RestControllers;

import com.fict.workinggroups.chess_puzzles.Entity.FenModel;
import com.fict.workinggroups.chess_puzzles.Repository.FenRepository;
import com.fict.workinggroups.chess_puzzles.Service.FenService;
import com.fict.workinggroups.chess_puzzles.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.util.List;


@RequestMapping("/api/fen")
@RestController
public class FenRestController {
@Autowired
    private FenRepository fenRepository;

@GetMapping
    public List<FenModel> getAllFens(){
    return fenRepository.findAll();
}

@PostMapping("/add")
    public FenModel saveFen(@ModelAttribute FenModel fenModel)
{
    return fenRepository.save(fenModel);
}

@GetMapping("/{id}")
    public ResponseEntity<FenModel> getFenById(@PathVariable long id)
{
    FenModel fenModel= fenRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id));
            return ResponseEntity.ok(fenModel);

}
    @PutMapping("/edit/{id}")
    public ResponseEntity<FenModel> updateFen(@PathVariable long id,@ModelAttribute FenModel fenModel) {
        FenModel updateFenmodel = fenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        updateFenmodel.setFen((fenModel.getFen()));
        updateFenmodel.setDescription((fenModel.getDescription()));


        fenRepository.save(updateFenmodel);

        return ResponseEntity.ok(updateFenmodel);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteFen(@PathVariable long id){

       FenModel fenModel = fenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        fenRepository.delete(fenModel);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}

//Create new FEN - POST