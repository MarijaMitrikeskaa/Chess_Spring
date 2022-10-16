package com.fict.workinggroups.chess_puzzles.Service;


import com.fict.workinggroups.chess_puzzles.Entity.Fen;

import java.util.List;

public interface FenService {
    List<Fen> getAllFens();
    void saveFen(Fen fen);
    Fen getFenById(long Id);
    void deleteFen (long Id);
}
