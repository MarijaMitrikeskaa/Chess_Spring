package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.exception.FenNotFound;
import com.fict.workinggroups.chess_puzzles.exception.PlayerNotFound;
import com.fict.workinggroups.chess_puzzles.model.Fen;
import com.fict.workinggroups.chess_puzzles.model.Player;
import com.fict.workinggroups.chess_puzzles.repository.PlayerRepository;
import com.fict.workinggroups.chess_puzzles.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayedServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Optional<Player> getPlayerById(String id){ return this.playerRepository.findById(id);}

    @Override
    public List<Player> getAllPlayers(){ return this.playerRepository.findAll();}

    @Override
    public Optional<Player> deletePlayer(String id) {
        if(!playerRepository.findById(id).isEmpty()) {
            this.playerRepository.deleteById(id);
            return playerRepository.findById(id);
        }
        else {
            throw new PlayerNotFound();
        }

    }






}
