package com.fict.workinggroups.chess_puzzles;

import com.fict.workinggroups.chess_puzzles.BoardMoves.TestMoves;
import com.fict.workinggroups.chess_puzzles.Entity.FenModel;
import com.fict.workinggroups.chess_puzzles.Repository.FenRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


@SpringBootApplication
public class ChessPuzzlesApplication {

    public static void main(String[] args) {

        SpringApplication.run(ChessPuzzlesApplication.class, args);

        TestMoves testMoves = new TestMoves();

        System.out.println(testMoves.getBoardModel());
    }


    @Autowired
    private FenRepository fenRepository;


    @Bean
    public CommandLineRunner dataLoader(FenRepository fenRepository) {
        return args -> {
            fenRepository.save(new FenModel("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", "The Starting Position"));
            fenRepository.save(new FenModel("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1", "This is after the move 1.e4"));
            fenRepository.save(new FenModel("3RQ3/pp3pk1/6p1/2q4p/5PbK/P1r5/2n3PP/7R b – – 0 1", "Black to play and mate white"));
            fenRepository.save(new FenModel("5k2/ppp5/4P3/3R3p/6P1/1K2Nr2/PP3P2/8 b - - 1 32", "The final position"));
            fenRepository.save(new FenModel("r3r3/3nkp2/q1p1p1p1/p3P1P1/PbpB4/1P5R/2P2Q1P/3RN2K w – – 0 1", "The checkmate, White to play and mate Black."));
            fenRepository.save(new FenModel("3rr1nk/1R2q3/3b3p/4np2/2Pp2pP/P2P2P1/5PBK/1RBQ2N1 b – – 0 1", "Black to play and win"));
        };
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}