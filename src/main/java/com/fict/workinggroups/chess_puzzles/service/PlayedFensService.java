package com.fict.workinggroups.chess_puzzles.service;

import com.fict.workinggroups.chess_puzzles.model.dto.PlayedFenDto;
import com.fict.workinggroups.chess_puzzles.model.entity.PlayedFen;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;

import java.util.Optional;

public interface PlayedFensService {

    Optional<PlayedFen> getPlayerFenById(String id);

    boolean checkSolution(PlayedFenDto playedFenDto);

    void playerPoints(PlayedFenDto playedFenDto, Player player, PlayedFen playedFen);

}
