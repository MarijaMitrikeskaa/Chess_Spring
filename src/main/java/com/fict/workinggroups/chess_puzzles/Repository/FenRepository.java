package com.fict.workinggroups.chess_puzzles.Repository;


import com.fict.workinggroups.chess_puzzles.Entity.Fen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FenRepository extends JpaRepository<Fen, Long> {
}
