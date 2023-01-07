package com.fict.workinggroups.chess_puzzles.service;

import com.fict.workinggroups.chess_puzzles.model.entity.Player;
import com.fict.workinggroups.chess_puzzles.model.entity.Tournament;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PlayerService {
    Optional<Player> getPlayerById(String id);

    List<Player> getAllPlayers();

    Optional<Player> deletePlayer(String id);


    Optional<Player> editPlayer(String id, String username);

    boolean hasId(String playerId);


}
