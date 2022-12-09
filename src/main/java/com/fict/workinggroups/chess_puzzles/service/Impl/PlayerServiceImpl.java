package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.exception.PlayerNotFound;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;
import com.fict.workinggroups.chess_puzzles.model.entity.Tournament;
import com.fict.workinggroups.chess_puzzles.model.entity.User;
import com.fict.workinggroups.chess_puzzles.repository.PlayerRepository;
import com.fict.workinggroups.chess_puzzles.repository.TournamentRepository;
import com.fict.workinggroups.chess_puzzles.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Override
    public Optional<Player> getPlayerById(String id) {
        return this.playerRepository.findById(id);
    }

    @Override
    public List<Player> getAllPlayers() {
        return this.playerRepository.findAll();
    }

    @Override
    public Optional<Player> deletePlayer(String id) {
        if (!playerRepository.findById(id).isEmpty()) {
            this.playerRepository.deleteById(id);
            return playerRepository.findById(id);
        } else {
            throw new PlayerNotFound();
        }

    }

    @Override
    public Optional<Player> addPlayer(String username) {

        return Optional.of(this.playerRepository.save(new Player()));
    }

//    @Override
//    public Set<Player> joinTournament(String tournamentId,Player player) {
//        if (this.tournamentRepository.findById(tournamentId).isPresent()) {
//            Set<Player>playersSet= this.tournamentRepository.findById(tournamentId).get().getPlayers();
//            playersSet.add(player);
//            return playersSet;
//        }
//        throw new IndexOutOfBoundsException();
//    }


}
