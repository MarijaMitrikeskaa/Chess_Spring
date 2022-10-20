package com.fict.workinggroups.chess_puzzles.Controllers;

import com.fict.workinggroups.chess_puzzles.Entity.FenModel;
import com.fict.workinggroups.chess_puzzles.Service.FenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FenRestController {
    @Autowired
    private FenService fenService;

    @GetMapping("/list")
    public List<FenModel> listFens() {
        return fenService.getAllFens();
    }
}

//Create new FEN - POST