package com.fict.workinggroups.chess_puzzles.Service;


import com.fict.workinggroups.chess_puzzles.Entity.FenModel;

import java.util.List;
import java.util.Optional;

public interface FenService {
    List<FenModel> getAllFens();

    void saveFen(FenModel fenModel);

    FenModel getFenById(String id);

    void deleteFen(String id);
    Optional<FenModel> edit(String id, String fen, String description);

    Optional<FenModel> findById(String id);
}
