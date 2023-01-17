package com.fict.workinggroups.chess_puzzles.service;

import com.fict.workinggroups.chess_puzzles.model.dto.TournamentDto;
import com.fict.workinggroups.chess_puzzles.model.entity.Fen;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;
import com.fict.workinggroups.chess_puzzles.model.entity.Tournament;
import com.fict.workinggroups.chess_puzzles.model.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TournamentService {

    Optional<Tournament> getTournamentById(String id);
    List<Tournament> getAllTournaments();

    Optional<Tournament> deleteTournament(String id);

    Optional<Tournament> saveTournament (TournamentDto tournamentDto);

//    Tournament addTournament(Tournament tournament);

    Set<Player> listPlayersInTournament(String tournamentId);
    Set<Fen> listFensInTournament(String tournamentId);
    void joinTournament(String id, User userId);
    Optional<Tournament> edit(String id, TournamentDto tournamentDto);

    Tournament findTournamentByName(String s);
//    Tournament saveT(String name);
}
