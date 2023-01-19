package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.exception.FenNotFound;
import com.fict.workinggroups.chess_puzzles.model.dto.PlayedFenDto;
import com.fict.workinggroups.chess_puzzles.model.entity.Fen;
import com.fict.workinggroups.chess_puzzles.model.entity.PlayedFen;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;
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
    public void playerPoints(PlayedFenDto playedFenDto, Player player, PlayedFen playedFen) {
        if (checkSolution(playedFenDto)) {
            int achievedPoints = playedFen.getPlayerPoints();
            player.setPoints(achievedPoints);
        }

    }


    @Override
    public boolean checkSolution(PlayedFenDto playedFenDto) {
        if (this.fenRepository.findById(playedFenDto.getFenId()).isPresent()) {
            Optional<Fen> fen = this.fenRepository.findById(playedFenDto.getFenId());
            String fenSolution = fen.get().getSolution();
            return fenSolution.equals(playedFenDto.getSolution());

            //cekor 1
            //treba da se zacuva vo playedFenRepository fenId, playerId, tournamentID, solution, actual points etc
            //actual points - ako maxPoints se 500, actual ako e tocno resenieto ke bidi primer 450, ako e gresno ke bidi 0

            //cekor 2
            //todo update the leaderboad !!!
            //leaderboadrService/repository - ako postoi toj player za toj natprevar (za toj torunament id)
            //togas samo dodaj mu poeni
            //ako ne postoi, napravi go i zacuvaj go vo leaderboard
            //EntityLeaderboard - PlayerNameNickName, TournamentId(obicen string, ne misli na relations),
            //                      points | kolku se tocni , kolku se izigrani

        } else {
            throw new FenNotFound();

        }
    }


    @Override
    public Optional<PlayedFen> getPlayerFenById(String id) {
        return this.playedFensRepository.findById(id);
    }


}

