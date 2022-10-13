package com.fict.workinggroups.chess_puzzles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/puzzles")
public class ChessPuzzlesController {

    @RequestMapping("/all")
    public List<PuzzleModel> all() {
        PuzzleModel model = new PuzzleModel();
        model.fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        model.description = "some description";

        PuzzleModel model2 = new PuzzleModel();
        model2.fen = "rnbqkbnr/pppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";

        ArrayList<PuzzleModel> list = new ArrayList<>();
        list.add(model);
        list.add(model);
        list.add(model2);
        return list;
    }

    //CRUD operations
    //Create (POST)
    //Read (GET)
    //Update (PUT)
    //Delete (DELETE)
}
