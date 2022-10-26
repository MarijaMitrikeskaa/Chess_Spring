package com.fict.workinggroups.chess_puzzles.Repository;


import com.fict.workinggroups.chess_puzzles.Entity.FenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FenRepository extends JpaRepository<FenModel, Long> {
}
//change the ID to string
//list of puzzles {1,2,3,4,...10000}

