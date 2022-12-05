package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.exception.PlayerNotFound;
import com.fict.workinggroups.chess_puzzles.exception.TournamentNotFound;
import com.fict.workinggroups.chess_puzzles.model.Player;
import com.fict.workinggroups.chess_puzzles.model.Tournament;
import com.fict.workinggroups.chess_puzzles.repository.TournamentRepository;
import com.fict.workinggroups.chess_puzzles.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;
@Service
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Override
    public Optional<Tournament> getTournamentById(String id) { return this.tournamentRepository.findById(id);}

    @Override
    public List<Tournament> getAllTournaments(){ return this.tournamentRepository.findAll();}

    @Override
    public Optional<Tournament> deleteTournament(String id) {
        if(!tournamentRepository.findById(id).isEmpty()) {
            this.tournamentRepository.deleteById(id);
            return tournamentRepository.findById(id);
        }
        else {
            throw new TournamentNotFound();
        }

    }

}
