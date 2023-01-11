package com.fict.workinggroups.chess_puzzles.repository;


import com.fict.workinggroups.chess_puzzles.model.entity.Fen;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FenRepository extends JpaRepository<Fen, String> {


    Optional<Fen> findById(String id);
}
//change the ID to string
//list of puzzles {1,2,3,4,...10000}

