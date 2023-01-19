package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.exception.FenNotFound;
import com.fict.workinggroups.chess_puzzles.model.dto.PlayedFenDto;
import com.fict.workinggroups.chess_puzzles.model.entity.Fen;
import com.fict.workinggroups.chess_puzzles.model.entity.PlayedFen;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;
import com.fict.workinggroups.chess_puzzles.model.entity.Tournament;
import com.fict.workinggroups.chess_puzzles.repository.FenRepository;
import com.fict.workinggroups.chess_puzzles.repository.PlayedFensRepository;
import com.fict.workinggroups.chess_puzzles.repository.PlayerRepository;
import com.fict.workinggroups.chess_puzzles.repository.TournamentRepository;
import com.fict.workinggroups.chess_puzzles.service.PlayedFensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayedFensServiceImpl implements PlayedFensService {

    @Autowired
    private PlayedFensRepository playedFensRepository;

    @Autowired
    private FenRepository fenRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private PlayerRepository playerRepository;


    @Override
    public  void playerPoints(PlayedFenDto playedFenDto,Player player, PlayedFen playedFen){
        if (checkSolution(playedFenDto)){
           int achievedPoints=playedFen.getPlayerPoints();
           player.setPoints(achievedPoints);
        }

    }



    @Override
    public boolean checkSolution(PlayedFenDto playedFenDto) {
        if (this.fenRepository.findById(playedFenDto.getFenId()).isPresent()) {
            Optional<Fen> fen = this.fenRepository.findById(playedFenDto.getFenId());
            String fenSolution = fen.get().getSolution();
            return fenSolution.equals(playedFenDto.getSolution());

        } else {
            throw new FenNotFound();

        }
    }


    @Override
    public Optional<PlayedFen> getPlayerFenById(String id) {
        return this.playedFensRepository.findById(id);
    }


}

