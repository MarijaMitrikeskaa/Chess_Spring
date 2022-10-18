package com.fict.workinggroups.chess_puzzles.Service.Impl;

import com.fict.workinggroups.chess_puzzles.Entity.FenModel;
import com.fict.workinggroups.chess_puzzles.Repository.FenRepository;
import com.fict.workinggroups.chess_puzzles.Service.FenService;
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
    public FenModel getFenById(long Id) {
        Optional<FenModel> optional = fenRepo.findById(Id);
        FenModel fenModel;
        if (optional.isPresent()) {
            fenModel = optional.get();
        } else {
            throw new RuntimeException("This FEN is not found with id: " + Id);
        }
        return fenModel;
    }

    @Override
    public void deleteFen(long Id) {
        this.fenRepo.deleteById(Id);
    }
}

