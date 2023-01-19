package com.fict.workinggroups.chess_puzzles.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Set;

@Entity
@Setter
@Getter
public class PlayedFen {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "fen_id")
    private Fen fenId;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player playerId;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournamentId;

    private int playerPoints;


    public PlayedFen() {

    }


    public PlayedFen(int playerPoints) {
        this.playerPoints=playerPoints;

    }
    public String getId() {
        return id;
    }

}
