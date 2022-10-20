package com.fict.workinggroups.chess_puzzles.Service;


import com.fict.workinggroups.chess_puzzles.Entity.FenModel;

import java.util.List;

public interface FenService {
    List<FenModel> getAllFens();

    void saveFen(FenModel fenModel);

    FenModel getFenById(long id);

    void deleteFen(long id);
}
