package com.fict.workinggroups.chess_puzzles.model.dto;

import lombok.Data;

@Data
public class TournamentDto {

    String id;
    String name;
    boolean tournamentActivated;

//todo think about date of the tournament

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
