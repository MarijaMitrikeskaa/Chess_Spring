package com.fict.workinggroups.chess_puzzles.service;

import com.fict.workinggroups.chess_puzzles.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    Optional<Player> getPlayerById(String id) ;

    List<Player> getAllPlayers();

    Optional<Player> deletePlayer(String id);

 }
