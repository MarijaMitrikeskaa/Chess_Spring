package com.fict.workinggroups.chess_puzzles.service;


import com.fict.workinggroups.chess_puzzles.model.entity.Fen;

import java.util.List;
import java.util.Optional;

public interface FenService {
    List<Fen> getAllFens();

    void saveFen(Fen fen);

    Optional<Fen> getFenById(String id);

    Optional deleteFen(String id);
    Optional<Fen> edit(String id, String fen, String description);

    Optional<Fen> findById(String id);
    boolean isValidFen(String fen);
}
