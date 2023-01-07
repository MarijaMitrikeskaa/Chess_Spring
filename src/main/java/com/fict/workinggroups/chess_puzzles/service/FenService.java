package com.fict.workinggroups.chess_puzzles.service;


import com.fict.workinggroups.chess_puzzles.model.dto.FenDto;
import com.fict.workinggroups.chess_puzzles.model.entity.Fen;

import java.util.List;
import java.util.Optional;

public interface FenService {
    List<Fen> getAllFens();

     Optional<Fen> saveFen(String fen,String description,int points,String solution);

    Optional<Fen> getFenById(String id);

    Optional deleteFen(String id);
    Optional<Fen> edit(String id, String fen, String description,int points,String solution);

    Optional<Fen> findById(String id);
    boolean isValidFen(String fen);

    Optional<Fen> save(FenDto fenDto);
    Optional<Fen> edit(String id,FenDto fenDto);
}
