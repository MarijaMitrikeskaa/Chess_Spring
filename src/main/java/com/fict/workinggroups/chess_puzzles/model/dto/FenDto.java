package com.fict.workinggroups.chess_puzzles.model.dto;


import com.fict.workinggroups.chess_puzzles.model.enums.Status;
import lombok.Data;

@Data
public class FenDto {

    String fen;
    String description;

    Integer maxPoints;
    Status status;



    public FenDto(String fen, String description, Integer maxPoints, Status status) {
        this.fen = fen;
        this.description = description;
        this.maxPoints = maxPoints;

        this.status = status;
    }

    public FenDto() {
    }
}
