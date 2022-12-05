package com.fict.workinggroups.chess_puzzles.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PlayedFen {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "fen_id_id")
    private Fen fenId;

    @ManyToOne
    @JoinColumn(name = "player_id_id")
    private Player playerId;

    @ManyToOne
    @JoinColumn(name = "tournament_id_id")
    private Tournament tournamentId;

    public PlayedFen() {

    }
}
