package com.fict.workinggroups.chess_puzzles.controller;

import com.fict.workinggroups.chess_puzzles.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PlayerContoller {

    @Autowired
     private PlayerService playerService;


}
