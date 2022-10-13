package com.fict.workinggroups.chess_puzzles;

import javax.persistence.Entity;

public class PuzzleModel {
    //FEN -> Android -> User makes move -> example G6 - E4

    //rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
    //rnbqkbnr/8/8/8/8/8/8/8/RNBQKBNR

    //rnbqkbnr
    //pppppppp
    //8
    //8
    //8
    //8
    //PPPPPPPP
    //RNBQKBNR

    public String fen;
    public String description;

    //public int rating;
    //public int solvedTimes;
//    public Date createdAt;
//    public User createdBy;
}
