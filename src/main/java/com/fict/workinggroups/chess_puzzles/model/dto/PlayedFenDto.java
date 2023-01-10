package com.fict.workinggroups.chess_puzzles.model.dto;


import lombok.Data;

@Data
public class PlayedFenDto {

    String fenId;
    String solution;

    public PlayedFenDto(String fenId, String solution) {
        this.fenId = fenId;
        this.solution = solution;
    }

    public PlayedFenDto() {


    }
}
