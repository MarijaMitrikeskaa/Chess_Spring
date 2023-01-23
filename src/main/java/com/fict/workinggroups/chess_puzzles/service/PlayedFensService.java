package com.fict.workinggroups.chess_puzzles.service;

import com.fict.workinggroups.chess_puzzles.model.dto.PlayedFenDto;
import com.fict.workinggroups.chess_puzzles.model.entity.PlayedFen;

import java.util.Optional;

public interface PlayedFensService {

    Optional<PlayedFen> getPlayerFenById(String id);

    boolean checkSolution(PlayedFenDto playedFenDto);

    void updateLeaderboard(PlayedFenDto playedFenDto);

}
