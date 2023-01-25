package com.fict.workinggroups.chess_puzzles.service;


import com.fict.workinggroups.chess_puzzles.model.dto.FenSolutionDto;
import com.fict.workinggroups.chess_puzzles.model.entity.Fen;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FenService {
    List<Fen> getAllFens();

    Optional<Fen> saveFen(String fen, String description, Integer maxPoints, String solution);

    Optional<Fen> getFenById(String id);

    Optional deleteFen(String id);

    Optional<Fen> edit(String id, String fen, String description, Integer maxPoints, String solution);

    Optional<Fen> findById(String id);

    boolean isValidFen(String fen);

    Optional<Fen> save(FenSolutionDto fenSolutionDto);

    Optional<Fen> edit(String id, FenSolutionDto fenSolutionDto);

}
