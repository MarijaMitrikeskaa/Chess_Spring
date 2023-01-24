package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.model.dto.PlayedFenDto;
import com.fict.workinggroups.chess_puzzles.model.entity.*;
import com.fict.workinggroups.chess_puzzles.repository.*;
import com.fict.workinggroups.chess_puzzles.service.PlayedFensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public List<String> findTournamentByPlayer(String playerId) {
        List<String> tournamentsName = new ArrayList<>();
        List<PlayedFen> playedFenList = this.playedFensRepository.findPlayedFensByPlayerId(playerId);
        for (PlayedFen playedFen : playedFenList) {
            tournamentsName.add(playedFen.getTournamentId().getName());
        }
        return tournamentsName.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findPlayersByTournament(String tournamentId) {
        List<String> playersName = new ArrayList<>();
        List<PlayedFen> playedFenList = this.playedFensRepository.findPlayedFensByTournamentId(tournamentId);
        for (PlayedFen playedFen : playedFenList) {
            playersName.add(playedFen.getPlayerId().getUsername());

        }

        return playersName.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PlayedFen> getPlayerFenById(String id) {
        return this.playedFensRepository.findById(id);
    }


}

