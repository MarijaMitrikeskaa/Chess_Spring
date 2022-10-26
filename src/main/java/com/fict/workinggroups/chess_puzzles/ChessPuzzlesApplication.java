package com.fict.workinggroups.chess_puzzles;

import com.fict.workinggroups.chess_puzzles.Entity.FenModel;
import com.fict.workinggroups.chess_puzzles.Repository.FenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ChessPuzzlesApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ChessPuzzlesApplication.class, args);
    }

    @Autowired
    private FenRepository fenRepository;

    @Override
    public void run(String... args) throws Exception {
        FenModel fenModel1 = new FenModel();
        fenModel1.setFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        fenModel1.setDescription("Description 1");
        fenModel1.setValidAnswer("A1", "B2")
        fenRepository.save(fenModel1);

        FenModel fenModel2 = new FenModel();
        fenModel2.setFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        fenModel2.setDescription("Description 2");
        fenRepository.save(fenModel2);

        FenModel fenModel3 = new FenModel();
        fenModel3.setFen("r1b1k1nr/p2p1pNp/n2B4/1p1NP2P/6P1/3P1Q2/P1P1K3/q5b1");
        fenModel3.setDescription("Description 3");
        fenRepository.save(fenModel3);

        for (int i = 0; i < 100; i++) {
            FenModel fenModel = new FenModel();
            fenModel.setFen("8/8/n2B4/1p1NP2P/6P1/3P1Q2/P1P1K3/q5b1");
            fenModel.setDescription("Description " + i);
            fenRepository.save(fenModel);
        }
    }
}