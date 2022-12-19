package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.exception.InvalidTournament;
import com.fict.workinggroups.chess_puzzles.exception.TournamentNotFound;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;
import com.fict.workinggroups.chess_puzzles.model.entity.Tournament;
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
    public void saveTournament(Tournament tournament) {
        if(this.tournamentRepository.findByName(tournament.getName()).isPresent()){
            throw new InvalidTournament(tournament.getName());}
        else {
        this.tournamentRepository.save(tournament);}
    }

//    @Override
//    public Tournament addTournament(Tournament tournament) {
//        return tournamentRepository.save(tournament);
//    }

    @Override
    public Set<Player> listPlayersInTournament(String tournamentId){
        if (this.tournamentRepository.findById(tournamentId).isPresent()){
            if (this.tournamentRepository.findById(tournamentId).get().getPlayers().size()>0)
                return this.tournamentRepository.findById(tournamentId).get().getPlayers();
        }
        throw new IndexOutOfBoundsException();
    }
    @Override
    public Set<Player> joinTournament2(String tournamentId,Player player) {
        if (this.tournamentRepository.findById(tournamentId).isPresent()) {
            Set<Player>playersSet= this.tournamentRepository.findById(tournamentId).get().getPlayers();
            playersSet.add(player);
            return playersSet;
        }
        throw new IndexOutOfBoundsException();
    }
    @Override
    public Tournament findTournamentByName(String s) throws InvalidTournament{
        return tournamentRepository.findByName(s).orElseThrow(()->new InvalidTournament(s));
    }

//    @Override
//    public Tournament saveT(String name) {
//        if (this.tournamentRepository.findByName(name).isPresent())
//            throw new InvalidTournament(name);
//
//        return tournamentRepository.save(name);
//    }
//    problem ima so prikazuvanjeto na poraka
}
