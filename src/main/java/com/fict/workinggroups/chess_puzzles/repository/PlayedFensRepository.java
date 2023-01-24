package com.fict.workinggroups.chess_puzzles.repository;

import com.fict.workinggroups.chess_puzzles.model.entity.Fen;
import com.fict.workinggroups.chess_puzzles.model.entity.PlayedFen;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayedFensRepository extends JpaRepository<PlayedFen, String> {

    List<PlayedFen> findPlayedFensByTournamentId(String tournamentId);

    List<PlayedFen> findPlayedFensByPlayerId(String playerId);

}
