package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.model.PlayedFen;
import com.fict.workinggroups.chess_puzzles.repository.PlayedFensRepository;
import com.fict.workinggroups.chess_puzzles.service.PlayedFensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayedFensServiceImpl implements PlayedFensService {

    @Autowired
    private PlayedFensRepository playedFensRepository;

    @Override
    public Optional<PlayedFen> getPlayerFenById(String id){return this.playedFensRepository.findById(id);}



}
