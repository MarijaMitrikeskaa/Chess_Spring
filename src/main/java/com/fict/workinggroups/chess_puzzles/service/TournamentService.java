package com.fict.workinggroups.chess_puzzles.service;

import com.fict.workinggroups.chess_puzzles.model.Tournament;

import java.util.List;
import java.util.Optional;

public interface TournamentService {

    Optional<Tournament> getTournamentById(String id);
    List<Tournament> getAllTournaments();

    Optional<Tournament> deleteTournament(String id);
}
