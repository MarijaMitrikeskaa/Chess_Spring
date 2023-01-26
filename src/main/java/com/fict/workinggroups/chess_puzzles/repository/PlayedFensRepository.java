package com.fict.workinggroups.chess_puzzles.repository;

import com.fict.workinggroups.chess_puzzles.model.entity.PlayedFen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayedFensRepository extends JpaRepository<PlayedFen, String> {

    List<PlayedFen> findPlayedFensByTournamentId(String tournamentId);

    List<PlayedFen> findPlayedFensByPlayerId(String playerId);

}
