package com.fict.workinggroups.chess_puzzles.web.controller;

import com.fict.workinggroups.chess_puzzles.exception.PlayerNotFound;
import com.fict.workinggroups.chess_puzzles.exception.TournamentNotFound;
import com.fict.workinggroups.chess_puzzles.model.entity.Leaderboard;
import com.fict.workinggroups.chess_puzzles.service.LeaderboardService;
import lombok.Getter;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    @GetMapping("/viewLeaderBoard/{id}")
    public String viewLeaderboard(Model model,@PathVariable String id)
    {
        model.addAttribute("leaderBoard", leaderboardService.getLeaderboardByTournamentId(id));
        return "leaderboard";
    }




}
