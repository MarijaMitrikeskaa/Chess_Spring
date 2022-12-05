package com.fict.workinggroups.chess_puzzles.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity

public class Player {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id_user_id")
    private User userId;

    @ManyToMany
    @JoinColumn(name = "fen_id_id")
    private List<Fen> fens;

    @ManyToMany
    private List<Tournament> tournaments;

    private int NumOfTournamenst;

    public Player() {
    }

    public Player(String id) {
        this.id=id;
    }

    public String getId() {
        return id;
    }
}
