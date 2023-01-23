package com.fict.workinggroups.chess_puzzles.model.dto;


import lombok.Data;

@Data
public class PlayedFenDto {

    String fenId;
    String playedSolution;//todo rename playedSolution

    String tournamentId;

    Integer actualPoints;

    String playerId;
    //tournament id
    //playerd id - nicknamne
    //actualPoints - bidejki nema da se maxPoints, za sekoja sekunda mu se namaluvaat poenite (453 points)

    public PlayedFenDto(String fenId, String playedSolution, String tournamentId, Integer actualPoints) {
        this.fenId = fenId;
        this.playedSolution = playedSolution;
        this.tournamentId = tournamentId;
        this.actualPoints = actualPoints;
    }

    public PlayedFenDto() {


    }
}
