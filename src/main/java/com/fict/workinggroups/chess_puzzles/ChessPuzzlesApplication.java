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
public class ChessPuzzlesApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ChessPuzzlesApplication.class, args);
	}
	@Autowired
	private FenRepository fenRepository;

	@Override
	public void run(String... args) throws Exception {
		FenModel fenModel1= new FenModel();
		fenModel1.setFen("P");
		fenModel1.setDescription("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
		fenRepository.save(fenModel1);

		FenModel fenModel2= new FenModel();
		fenModel2.setFen("b");
		fenModel2.setDescription("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
		fenRepository.save(fenModel2);

		FenModel fenModel3= new FenModel();
		fenModel3.setFen("n");
		fenModel3.setDescription("r1b1k1nr/p2p1pNp/n2B4/1p1NP2P/6P1/3P1Q2/P1P1K3/q5b1");
		fenRepository.save(fenModel3);

	}
}

//todo add mock data with command line runner