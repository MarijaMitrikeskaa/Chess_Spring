package com.fict.workinggroups.chess_puzzles.repository;

import com.fict.workinggroups.chess_puzzles.model.entity.PlayedFen;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayedFensRepository extends JpaRepository<PlayedFen, String> {

    Optional<List<Player>> findPlayedFensByTournamentId(String tournamentId);
    //petko mrdnal A1-B2
    //petko mrdnal A1-B5
    //trajko mrdnal A1-B2
    //petko mrdnal A1-B2

    //vo service remove duplicates - filter by unique
    //--> [petko, trajko]

    Optional<List<Player>> findPlayedFensByPlayerId(String playerId);
    //petko izigral na TournamentFeb2023
    //petko izigral na TournamentFeb2023
    //petko izigral na TournamentFeb2023
    //petko izigral na TournamentMay2023

    //- [TournamentMay2023, TournamentFeb2023]
}
