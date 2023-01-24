package com.fict.workinggroups.chess_puzzles.model.dto;


import lombok.Data;

@Data
public class PlayedFenDto {

    String fenId;
    String playedSolution;

    String tournamentId;

    Integer actualPoints;

    String playerId;

    public PlayedFenDto(String fenId, String playedSolution, String tournamentId, Integer actualPoints) {
        this.fenId = fenId;
        this.playedSolution = playedSolution;
        this.tournamentId = tournamentId;
        this.actualPoints = actualPoints;
    }

    public PlayedFenDto() {


    }
}
