package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.model.dto.FenDto;
import com.fict.workinggroups.chess_puzzles.model.entity.Fen;
import com.fict.workinggroups.chess_puzzles.repository.FenRepository;
import com.fict.workinggroups.chess_puzzles.service.FenService;
import com.fict.workinggroups.chess_puzzles.exception.FenNotFound;
import com.fict.workinggroups.chess_puzzles.exception.InvalidFenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
        return fenRepo.findAll();
    }

    @Override
    public Optional<Fen> saveFen(String fen, String description, String solution) {
        if (isValidFen(fen)) {
            Fen newFen = new Fen(fen, description, solution);
            this.fenRepo.save(newFen);
            return Optional.of(newFen);
        } else {
            throw new InvalidFenException();
        }


        //        if (isValidFen(fen.getFen())) {
//            this.fenRepo.save(fen.getFen(),fen.getDescription(),fen.getPoints(),fen.getSolution());
//        }
//        else {
//            throw new InvalidFenException();
//        }
    }

    @Override
    public Optional<Fen> getFenById(String id) {
        if (!fenRepo.findById(id).isEmpty()) {
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
    public Optional<Fen> deleteFen(String id) {
        if (!fenRepo.findById(id).isEmpty()) {
            this.fenRepo.deleteById(id);
            return fenRepo.findById(id);
        } else {
            throw new FenNotFound();
        }

    }

    @Override
    public Optional<Fen> edit(String id, String fen, String description, String solution) {
        Fen fenModel = this.fenRepo.findById(id).orElseThrow(() -> new InvalidFenException());
        fenModel.setFen(fen);
        fenModel.setDescription(description);

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

    // Rest methods

    @Override
    public Optional<Fen> save(FenDto fenDto) {
        if (isValidFen(fenDto.getFen())) {
            Fen fen = new Fen(fenDto.getFen(), fenDto.getDescription());
            this.fenRepo.save(fen);
            return Optional.of(fen);
        } else {
            throw new InvalidFenException();
        }
    }

    @Override
    public Optional<Fen> edit(String id, FenDto fenDto) {
        Fen fen = this.fenRepo.findById(id).orElseThrow(InvalidFenException::new);
        fen.setFen(fenDto.getFen());
        fen.setDescription(fenDto.getDescription());


        return Optional.of(this.fenRepo.save(fen));
    }
}
