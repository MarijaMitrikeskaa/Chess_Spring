package com.fict.workinggroups.chess_puzzles.repository;

import com.fict.workinggroups.chess_puzzles.entity.Guest;
import com.fict.workinggroups.chess_puzzles.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, String > {

}
