package com.fict.workinggroups.chess_puzzles.repository;


import com.fict.workinggroups.chess_puzzles.model.entity.Fen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FenRepository extends JpaRepository<Fen, String> {

}
//change the ID to string
//list of puzzles {1,2,3,4,...10000}

