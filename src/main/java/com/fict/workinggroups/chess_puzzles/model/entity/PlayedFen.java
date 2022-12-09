package com.fict.workinggroups.chess_puzzles.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.security.Timestamp;

@Entity
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

    public PlayedFen() {

    }

    public String getId() {
        return id;
    }

    public Timestamp getDate() {
        return date;
    }

    public Fen getFenId() {
        return fenId;
    }

    public Player getPlayerId() {
        return playerId;
    }

    public Tournament getTournamentId() {
        return tournamentId;
    }
}
