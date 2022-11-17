package com.fict.workinggroups.chess_puzzles.repository;


import com.fict.workinggroups.chess_puzzles.entity.FenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FenRepository extends JpaRepository<FenModel, String> {

}
//change the ID to string
//list of puzzles {1,2,3,4,...10000}

