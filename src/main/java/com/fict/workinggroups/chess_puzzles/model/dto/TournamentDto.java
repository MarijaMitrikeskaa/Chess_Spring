package com.fict.workinggroups.chess_puzzles.model.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class TournamentDto {

    String id;
    String name;
    boolean tournamentActivated;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate date;

//todo think about date of the tournament


    public TournamentDto(String name, boolean tournamentActivated, LocalDate date) {
        // this.id = id;
        this.name = name;
        this.tournamentActivated = tournamentActivated;
        this.date = date;

    }

    public TournamentDto() {
    }


}
