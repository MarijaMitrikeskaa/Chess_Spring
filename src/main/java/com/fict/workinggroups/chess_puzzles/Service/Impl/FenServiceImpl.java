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
        Optional<FenModel> optional = fenRepo.findById(id);
        FenModel fenModel;
        if (optional.isPresent()) {
            fenModel = optional.get();
        } else {
            throw new RuntimeException("This FEN is not found with id: " + id);
        }
        return fenModel;
    }
    @Override
    public Optional<FenModel>findById(Long id){
        return this.fenRepo.findById(id);
    }
    @Override
    public Optional<FenModel> edit(Long id, String fen, String description) {
        FenModel fenModel=this.fenRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        fenModel.setFen(fen);
        fenModel.setDescription(description);
        return Optional.of(this.fenRepo.save(fenModel));

    }

    @Override
    public void deleteFen(long id) {
        this.fenRepo.deleteById(id);
    }
}
