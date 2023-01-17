package com.fict.workinggroups.chess_puzzles.model.dto;

import com.fict.workinggroups.chess_puzzles.model.entity.Fen;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TournamentDto {

    String id;
    String name;
    boolean tournamentActivated;


//    private Set<Fen> fens=new HashSet<>();

    public TournamentDto(String id, String name, boolean tournamentActivated) {
        this.id = id;
        this.name = name;
        this.tournamentActivated = tournamentActivated;

    }

    public TournamentDto() {
    }

//    public Set<Fen> getFens() {
//        return fens;
//    }
//
//    public void setFens(Set<Fen> fens) {
//        this.fens = fens;
//    }
//}
}
