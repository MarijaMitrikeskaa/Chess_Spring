package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.exception.PlayedFenAlreadyExistsException;
import com.fict.workinggroups.chess_puzzles.exception.PlayedFenNotFound;
import com.fict.workinggroups.chess_puzzles.exception.PlayerNotFound;
import com.fict.workinggroups.chess_puzzles.model.dto.PlayedFenDto;
import com.fict.workinggroups.chess_puzzles.model.entity.*;
import com.fict.workinggroups.chess_puzzles.repository.*;
import com.fict.workinggroups.chess_puzzles.service.PlayedFensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        Optional<Tournament> tournament = this.tournamentRepository.findById(playedFenDto.getTournamentId());

        Optional<Player> player=this.playerRepository.findByUsername(playedFenDto.getUsername());
        if (player.isPresent()){
            PlayedFen playedFen = new PlayedFen(fen.get(), player.get(),
                    tournament.get(), playedFenDto.getActualPoints(), playedFenDto.getPlayedSolution());
            this.playedFensRepository.save(playedFen);

        }
        else {
            Player player1=new Player(playedFenDto.getUsername());
            this.playerRepository.save(player1);
            PlayedFen playedFen = new PlayedFen(fen.get(), player1,
                    tournament.get(), playedFenDto.getActualPoints(), playedFenDto.getPlayedSolution());
            this.playedFensRepository.save(playedFen);
        }



        Integer numberOfPlayedPuzzles = 0;

        Integer numberOfCorrectPlayedPuzzles = 0;

        Integer numberOfIncorrectPlayedPuzzles = 0;

        Leaderboard leaderboard = this.leaderboardRepository.findLeaderboardByNicknameAndTournamentId(player.get().getUsername(), playedFenDto.getTournamentId());
        if (leaderboard != null) {
            if (checkSolution(playedFenDto)) {
                Integer leadPoints = leaderboard.getPoints();
                leadPoints += playedFenDto.getActualPoints();
                leaderboard.setPoints(leadPoints);
                leaderboard.setNumberOfPlayedPuzzles(leaderboard.getNumberOfPlayedPuzzles() + 1);
                leaderboard.setNumberOfCorrectPlayedPuzzles(leaderboard.getNumberOfCorrectPlayedPuzzles() + 1);
                this.leaderboardRepository.save(leaderboard);
            } else {
                leaderboard.setNumberOfPlayedPuzzles(leaderboard.getNumberOfPlayedPuzzles() + 1);
                leaderboard.setNumberOfIncorrectPlayedPuzzles(leaderboard.getNumberOfPlayedPuzzles() - leaderboard.getNumberOfCorrectPlayedPuzzles());
                this.leaderboardRepository.save(leaderboard);
            }
        } else {
            if (checkSolution(playedFenDto)) {
                numberOfPlayedPuzzles++;
                numberOfCorrectPlayedPuzzles++;
                Leaderboard leaderboard1 = new Leaderboard(player.get().getUsername(), playedFenDto.getActualPoints(), playedFenDto.getTournamentId()
                        , numberOfPlayedPuzzles, numberOfCorrectPlayedPuzzles, numberOfIncorrectPlayedPuzzles);
                this.leaderboardRepository.save(leaderboard1);

            } else {
                numberOfCorrectPlayedPuzzles++;
                numberOfIncorrectPlayedPuzzles++;
                Leaderboard leaderboard1 = new Leaderboard(player.get().getUsername(), 0, playedFenDto.getTournamentId()
                        , numberOfPlayedPuzzles, numberOfCorrectPlayedPuzzles, numberOfIncorrectPlayedPuzzles);
                this.leaderboardRepository.save(leaderboard1);

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

    @Override
    public Optional<PlayedFen> savePlayedFen(PlayedFen playedFen) {
        if (this.playedFensRepository.findPlayedFenByFenIdAndPlayerId(playedFen.getFenId().getId(),playedFen.getPlayerId().getId()).isPresent()) {
            throw new PlayedFenAlreadyExistsException();
        }
        PlayedFen playedFen1 = new PlayedFen(playedFen.getFenId(),playedFen.getPlayerId(),playedFen.getTournamentId(),playedFen.getActualPoints(),playedFen.getPlayedSolution());


        return Optional.of(this.playedFensRepository.save(playedFen1));
    }


}

