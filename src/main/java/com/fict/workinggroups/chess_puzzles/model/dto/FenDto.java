package com.fict.workinggroups.chess_puzzles.model.dto;


import com.fict.workinggroups.chess_puzzles.model.enums.Status;
import lombok.Data;

@Data
public class FenDto {

    String fen;
    String description;
    int points;


    public FenDto(String fen, String description,int points) {
        this.fen = fen;
        this.description = description;
        this.points=points;
    }

    public FenDto() {
    }
}
