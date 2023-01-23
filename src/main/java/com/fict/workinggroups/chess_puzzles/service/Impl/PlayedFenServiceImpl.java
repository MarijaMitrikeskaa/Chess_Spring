package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.model.dto.PlayedFenDto;
import com.fict.workinggroups.chess_puzzles.model.entity.*;
import com.fict.workinggroups.chess_puzzles.repository.*;
import com.fict.workinggroups.chess_puzzles.service.PlayedFensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PlayedFenServiceImpl implements PlayedFensService {

    @Autowired
    private PlayedFensRepository playedFensRepository;

    @Autowired
    private FenRepository fenRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private LeaderboardRepository leaderboardRepository;


    @Override
    public void updateLeaderboard(PlayedFenDto playedFenDto) {
        Optional<Fen> fen = this.fenRepository.findById(playedFenDto.getFenId());

        Optional<Player> player = this.playerRepository.findById(playedFenDto.getPlayerId());

        Optional<Tournament> tournament = this.tournamentRepository.findById(playedFenDto.getTournamentId());

        PlayedFen playedFen = new PlayedFen(fen.get(), player.get(),
                tournament.get(), playedFenDto.getActualPoints(), playedFenDto.getPlayedSolution());
        this.playedFensRepository.save(playedFen);


        Set<Leaderboard> leaderboards = this.leaderboardRepository.findAllByTournamentId(playedFenDto.getTournamentId());


        if (checkSolution(playedFenDto)) {

            if (!leaderboards.isEmpty()) {
                for (Leaderboard lead : leaderboards) {

                    if (lead.getNickname().equals(player.get().getUsername())) {
                        Integer leadPoints = lead.getPoints();
                        leadPoints += playedFenDto.getActualPoints();
                        lead.setPoints(leadPoints);
                        this.leaderboardRepository.save(lead);

                    }


                }
            } else {
                Leaderboard leaderboard = new Leaderboard(player.get().getUsername(), playedFenDto.getActualPoints(), playedFenDto.getTournamentId());
                this.leaderboardRepository.save(leaderboard);
            }
        }


    }


    @Override
    public boolean checkSolution(PlayedFenDto playedFenDto) {


        if (this.fenRepository.findById(playedFenDto.getFenId()).isPresent()) {
            Optional<Fen> fen = this.fenRepository.findById(playedFenDto.getFenId());
            String fenSolution = fen.get().getSolution();

            return fenSolution.equals(playedFenDto.getPlayedSolution());
        }
        return false;
    }


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


    @Override
    public Optional<PlayedFen> getPlayerFenById(String id) {
        return this.playedFensRepository.findById(id);
    }


}

