package com.fict.workinggroups.chess_puzzles.service;


import com.fict.workinggroups.chess_puzzles.entity.FenModel;

import java.util.List;
import java.util.Optional;

public interface FenService {
    List<FenModel> getAllFens();

    void saveFen(FenModel fenModel);

    Optional<FenModel> getFenById(String id);

    Optional deleteFen(String id);
    Optional<FenModel> edit(String id, String fen, String description);

    Optional<FenModel> findById(String id);
    boolean isValidFen(String fen);
}
