package com.fict.workinggroups.chess_puzzles.Service.Impl;
import com.fict.workinggroups.chess_puzzles.Entity.Fen;
import com.fict.workinggroups.chess_puzzles.Repository.FenRepository;
import com.fict.workinggroups.chess_puzzles.Service.FenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.management.relation.RelationServiceNotRegisteredException;
import java.util.List;
import java.util.Optional;
@Service
public class FenServiceImpl  implements FenService {

    @Autowired
    private FenRepository fenRepo;

    @Override
    public List<Fen> getAllFens() {
        return fenRepo.findAll();
    }

    @Override
    public void saveFen(Fen fen) {
        this.fenRepo.save(fen);
    }

    @Override
    public Fen getFenById(long Id) {
        Optional<Fen> optional = fenRepo.findById(Id);
        Fen fen = null;
        if (optional.isPresent()) {
            fen = optional.get();
        }else {
            throw  new RuntimeException(" This FEN is not found with id : " + Id);
        }
        return fen;

    }

    @Override
    public void deleteFen(long Id) {
        this.fenRepo.deleteById(Id);
    }
}

