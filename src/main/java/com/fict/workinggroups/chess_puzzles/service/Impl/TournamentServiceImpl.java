package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.exception.InvalidTournament;
import com.fict.workinggroups.chess_puzzles.exception.TournamentNotFound;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;
import com.fict.workinggroups.chess_puzzles.model.entity.Tournament;
import com.fict.workinggroups.chess_puzzles.model.entity.User;
import com.fict.workinggroups.chess_puzzles.repository.PlayerRepository;
import com.fict.workinggroups.chess_puzzles.repository.TournamentRepository;
import com.fict.workinggroups.chess_puzzles.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Optional<Tournament> getTournamentById(String id) {
        return this.tournamentRepository.findById(id);
    }

    @Override
    public List<Tournament> getAllTournaments() {
        return this.tournamentRepository.findAll();
    }

    @Override
    public Optional<Tournament> deleteTournament(String id) {
        if (!tournamentRepository.findById(id).isEmpty()) {
            this.tournamentRepository.deleteById(id);
            return tournamentRepository.findById(id);
        } else {
            throw new TournamentNotFound();
        }

    }

    @Override
    public Optional<Tournament> edit(String id, String name, boolean tournamentActivated) {
        Tournament tournament = this.tournamentRepository.findById(id).orElseThrow(TournamentNotFound::new);


        tournament.setName(name);
        tournament.setTournamentActive(tournamentActivated);

        return Optional.of(this.tournamentRepository.save(tournament));

    }

    @Override
    public void saveTournament(Tournament tournament) {
        if (this.tournamentRepository.findByName(tournament.getName()).isPresent()) {
            throw new InvalidTournament(tournament.getName());
        }


        this.tournamentRepository.save(tournament);


    }


    @Override
    public Set<Player> listPlayersInTournament(String tournamentId) {
        if (this.tournamentRepository.findById(tournamentId).isPresent()) {
            if (this.tournamentRepository.findById(tournamentId).get().getPlayers().size() > 0)
                return this.tournamentRepository.findById(tournamentId).get().getPlayers();
        }
        return this.tournamentRepository.findById(tournamentId).get().getPlayers();


    }

    @Override
    public Tournament findTournamentByName(String s) throws InvalidTournament {
        return tournamentRepository.findByName(s).orElseThrow(() -> new InvalidTournament(s));
    }

    @Override
    public void joinTournament(String id, User userId) {


        Optional<Player> player = this.playerRepository.findByUserId(userId);

        Optional<Tournament> tournament = this.tournamentRepository.findById(id);
        Set<Player> players = tournament.get().getPlayers();
        players.add(player.orElseThrow());
        tournamentRepository.save(tournament.get());


    }


}
