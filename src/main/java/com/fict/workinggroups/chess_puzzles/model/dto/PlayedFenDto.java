package com.fict.workinggroups.chess_puzzles.model.dto;


import lombok.Data;

@Data
public class PlayedFenDto {

    String fenId;
    String solution;//todo rename playedSolution
    //tournament id
    //playerd id - nicknamne
    //actualPoints - bidejki nema da se maxPoints, za sekoja sekunda mu se namaluvaat poenite (453 points)

    public PlayedFenDto(String fenId, String solution) {
        this.fenId = fenId;
        this.solution = solution;
    }

    public PlayedFenDto() {


    }
}
