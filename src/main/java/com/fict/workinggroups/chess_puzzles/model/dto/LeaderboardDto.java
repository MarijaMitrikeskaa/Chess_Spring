package com.fict.workinggroups.chess_puzzles.model.dto;


import lombok.Data;

@Data
public class LeaderboardDto {

    String name;

    Integer points;

    public LeaderboardDto() {
    }

    public LeaderboardDto(String name, Integer points) {
        this.name = name;
        this.points = points;
    }
}
