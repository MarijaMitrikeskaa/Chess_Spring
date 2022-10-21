package com.fict.workinggroups.chess_puzzles.Service;


import com.fict.workinggroups.chess_puzzles.Entity.FenModel;

import java.util.List;
import java.util.Optional;

public interface FenService {
    List<FenModel> getAllFens();

    void saveFen(FenModel fenModel);

    FenModel getFenById(long id);

    void deleteFen(long id);
    Optional<FenModel> edit(Long id, String fen, String description);

    Optional<FenModel> findById(Long id);
}
