package com.fict.workinggroups.chess_puzzles;

import com.fict.workinggroups.chess_puzzles.Entity.FenModel;
import com.fict.workinggroups.chess_puzzles.Service.FenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FooRestController {

    @Autowired
    private FenService fenSrevice;

    @RequestMapping("/fooRest")
    public List<FenModel> fooMethod() {
        return fenSrevice.getAllFens();
    }


}
