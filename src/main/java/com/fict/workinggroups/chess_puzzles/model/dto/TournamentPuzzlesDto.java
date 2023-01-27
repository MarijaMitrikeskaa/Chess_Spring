package com.fict.workinggroups.chess_puzzles.model.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TournamentPuzzlesDto {

    String id;
    String name;
    boolean tournamentActivated;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime date;

    List<FenDto> puzzleList;

    public TournamentPuzzlesDto(String name, boolean tournamentActivated, LocalDateTime date) {
        this.name = name;
        this.tournamentActivated = tournamentActivated;
        this.date = date;

    }

    public TournamentPuzzlesDto() {
    }


}
