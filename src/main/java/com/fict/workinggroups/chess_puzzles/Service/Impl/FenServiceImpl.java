package com.fict.workinggroups.chess_puzzles.Service.Impl;

import com.fict.workinggroups.chess_puzzles.Entity.FenModel;
import com.fict.workinggroups.chess_puzzles.Repository.FenRepository;
import com.fict.workinggroups.chess_puzzles.Service.FenService;
import com.fict.workinggroups.chess_puzzles.exception.ResourceNotFoundException;
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
    public List<FenModel> getAllFens() {
        return fenRepo.findAll();
    }

    @Override
    public void saveFen(FenModel fenModel) {


        if (isValidFen(fenModel.getFen()))
            this.fenRepo.save(fenModel);
        else System.out.println("Invalid fen");




    }

    @Override
    public FenModel getFenById(String id) {
        return null;
    }

    @Override
    public Optional<FenModel>findById(String id){
        return this.fenRepo.findById(id);
    }

    @Override
    public void deleteFen(String id) {
        this.fenRepo.deleteById(id);
    }

    @Override
    public Optional<FenModel> edit(String id, String fen, String description) {
        return Optional.empty();
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
}
