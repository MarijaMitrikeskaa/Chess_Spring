package com.fict.workinggroups.chess_puzzles.service;

import com.fict.workinggroups.chess_puzzles.model.entity.Player;
import com.fict.workinggroups.chess_puzzles.model.entity.Tournament;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TournamentService {

    Optional<Tournament> getTournamentById(String id);
    List<Tournament> getAllTournaments();

    Optional<Tournament> deleteTournament(String id);

    void saveTournament (Tournament tournament);

//    Tournament addTournament(Tournament tournament);

    Set<Player> listPlayersInTournament(String tournamentId);
    Set<Player> joinTournament2(String tournamentId,Player player);

    Tournament findTournamentByName(String s);
//    Tournament saveT(String name);
}
