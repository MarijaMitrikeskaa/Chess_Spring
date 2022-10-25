package com.fict.workinggroups.chess_puzzles.Service.Impl;

import com.fict.workinggroups.chess_puzzles.Entity.FenModel;
import com.fict.workinggroups.chess_puzzles.Repository.FenRepository;
import com.fict.workinggroups.chess_puzzles.Service.FenService;
import com.fict.workinggroups.chess_puzzles.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

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
        this.fenRepo.save(fenModel);
    }

    @Override
    public FenModel getFenById(long id) {
        return null;
    }

    @Override
    public Optional<FenModel>findById(Long id){
        return this.fenRepo.findById(id);
    }

    @Override
    public void deleteFen(long id) {
        this.fenRepo.deleteById(id);
    }

    @Override
    public Optional<FenModel> edit(Long id, String fen, String description) {
        return Optional.empty();
    }
}
