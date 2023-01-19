package com.fict.workinggroups.chess_puzzles.model.dto;


import lombok.Data;

@Data
public class FenDto {

    String fen;
    String description;
//todo add maxPoints, solution(A1-B2) - po default e vo pending status

    public FenDto(String fen, String description) {
        this.fen = fen;
        this.description = description;

    }

    public FenDto() {
    }
}
