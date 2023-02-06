package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.exception.FenNotFound;
import com.fict.workinggroups.chess_puzzles.exception.InvalidFenException;
import com.fict.workinggroups.chess_puzzles.model.dto.FenSolutionDto;
import com.fict.workinggroups.chess_puzzles.model.entity.Fen;
import com.fict.workinggroups.chess_puzzles.model.enums.Status;
import com.fict.workinggroups.chess_puzzles.repository.FenRepository;
import com.fict.workinggroups.chess_puzzles.service.FenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FenServiceImpl implements FenService {

    @Autowired
    private FenRepository fenRepo;

    @Override
    public List<Fen> getAllFens() {
        List<Fen> fens = this.fenRepo.findAll();
        //hacks List<Fen> fens = this.fenRepo.findBySolutionIsNull();
        List<Fen> Fens = new ArrayList<>();
        for (Fen fen : fens) {
            Fen fen1 = new Fen(fen.getId(), fen.getFen(), fen.getDescription(), fen.getMaxPoints());
            Fens.add(fen1);
        }


        return Fens;
    }

    @Override
    public List<Fen> getAllFensWithSolution() {
        return fenRepo.findAll();
    }

    @Override
    public Optional<Fen> saveFen(String fen, String description, Integer maxPoints, String solution) {
        if (isValidFen(fen)) {
            Fen newFen = new Fen(fen, description, maxPoints, solution);
            this.fenRepo.save(newFen);
            return Optional.of(newFen);
        } else {
            throw new InvalidFenException();
        }
    }

    @Override
    public Optional<Fen> getFenById(String id) {
        if (fenRepo.findById(id).isPresent()) {
            return this.fenRepo.findById(id);

        } else {
            throw new FenNotFound();
        }
    }

    @Override
    public Optional<Fen> findById(String id) {
        return this.fenRepo.findById(id);
    }

    @Override
    public String deleteFen(String id) {
        if (!fenRepo.findById(id).isEmpty()) {
            this.fenRepo.deleteById(id);
            return "deleted";//fenRepo.findById(id);
        } else {
            return "not deleted";
        }

    }

    @Override
    public Optional<Fen> edit(String id, String fen, String description, Integer maxPoints, String solution) {
        Fen fenModel = this.fenRepo.findById(id).orElseThrow(InvalidFenException::new);
        fenModel.setFen(fen);
        fenModel.setDescription(description);
        fenModel.setMaxPoints(maxPoints);

        fenModel.setSolution(solution);
        return Optional.of(this.fenRepo.save(fenModel));
    }


    public boolean isValidFen(String fen) {
        Pattern pattern = Pattern.compile("((([prnbqkPRNBQK12345678]*/){7})([prnbqkPRNBQK12345678]*)) (w|b) ((K?Q?k?q?)|\\-) (([abcdefgh][36])|\\-) (\\d*) (\\d*)");
        Matcher matcher = pattern.matcher(fen);
        if (!matcher.matches()) {
            System.out.println("Invalid FEN, not following specified format.");
            return false;
        }
        return true;
    }


    @Override
    public Optional<Fen> save(FenSolutionDto fenSolutionDto) {
        if (isValidFen(fenSolutionDto.getFen())) {
            Fen fen = new Fen(fenSolutionDto.getFen(), fenSolutionDto.getDescription(), fenSolutionDto.getMaxPoints(), fenSolutionDto.getSolution());
            fen.setStatus(Status.PENDING);
            this.fenRepo.save(fen);
            return Optional.of(fen);
        } else {
            throw new InvalidFenException();
        }
    }

    @Override
    public Optional<Fen> edit(String id, FenSolutionDto fenSolutionDto) {
        Fen fen = this.fenRepo.findById(id).orElseThrow(InvalidFenException::new);
        fen.setFen(fenSolutionDto.getFen());
        fen.setDescription(fenSolutionDto.getDescription());
        fen.setMaxPoints(fenSolutionDto.getMaxPoints());
        fen.setStatus(fenSolutionDto.getStatus());

        return Optional.of(this.fenRepo.save(fen));
    }

    @Override
    public Optional<Fen> addFenSolution(String id, String solution) {
        Fen fen = this.fenRepo.findById(id).orElseThrow(InvalidFenException::new);
        fen.setSolution(solution);

        return Optional.of(this.fenRepo.save(fen));
    }
}
