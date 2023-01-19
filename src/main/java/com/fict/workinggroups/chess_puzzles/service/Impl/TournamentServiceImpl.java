package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.exception.InvalidFenException;
import com.fict.workinggroups.chess_puzzles.exception.InvalidTournament;
import com.fict.workinggroups.chess_puzzles.exception.TournamentNotFound;
import com.fict.workinggroups.chess_puzzles.model.dto.FenDto;
import com.fict.workinggroups.chess_puzzles.model.dto.TournamentDto;
import com.fict.workinggroups.chess_puzzles.model.entity.Fen;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;
import com.fict.workinggroups.chess_puzzles.model.entity.Tournament;
import com.fict.workinggroups.chess_puzzles.model.entity.User;
import com.fict.workinggroups.chess_puzzles.repository.FenRepository;
import com.fict.workinggroups.chess_puzzles.repository.PlayerRepository;
import com.fict.workinggroups.chess_puzzles.repository.TournamentRepository;
import com.fict.workinggroups.chess_puzzles.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private FenRepository fenRepository;

    @Override
    public Optional<Tournament> getTournamentById(String id) {
        if (!tournamentRepository.findById(id).isEmpty()) {
            return this.tournamentRepository.findById(id);
        } else {
            throw new TournamentNotFound();
        }
    }

    @Override
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
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
    public Optional<Tournament> edit(String id, TournamentDto tournamentDto) {
        Tournament tournament = this.tournamentRepository.findById(id).orElseThrow(TournamentNotFound::new);


        tournament.setName(tournamentDto.getName());
        tournament.setTournamentActive(tournamentDto.isTournamentActivated());

        return Optional.of(this.tournamentRepository.save(tournament));

    }


    @Override
    public Optional<Tournament> saveTournament(TournamentDto tournamentDto) {
        if (this.tournamentRepository.findByName(tournamentDto.getName()).isPresent()) {
            throw new InvalidTournament(tournamentDto.getName());
        }
        Tournament tournament = new Tournament(tournamentDto.getName());
        List<Fen> fens = this.fenRepository.findAll();
        Set<Fen> fenSet = new HashSet<>(fens);

        tournament.setFens(fenSet);

        return Optional.of(this.tournamentRepository.save(tournament));

//        public Optional<Fen> save(FenDto fenDto) {
//            if (isValidFen(fenDto.getFen())) {
//                Fen fen=new Fen(fenDto.getFen(),fenDto.getDescription(),fenDto.getPoints());
//                this.fenRepo.save(fen);
//                return Optional.of(fen);
//            }
//            else {
//                throw new InvalidFenException();
//            }
//        }


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
    public Set<Fen> listFensInTournament(String tournamentId) {
        if (this.tournamentRepository.findById(tournamentId).isPresent()) {
            if (this.tournamentRepository.findById(tournamentId).get().getFens().size() > 0)
                return this.tournamentRepository.findById(tournamentId).get().getFens();
        }
        return this.tournamentRepository.findById(tournamentId).get().getFens();


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