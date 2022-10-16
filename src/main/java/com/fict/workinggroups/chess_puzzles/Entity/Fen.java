package com.fict.workinggroups.chess_puzzles.Entity;

import javax.persistence.*;

@Entity
@Table (name = "fens")
public class Fen {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(name = "TheFens")
    private String Fen;

    @Column(name = "TheDescription")
    private String description;

    public Fen() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getFen() {
        return Fen;
    }

    public void setFen(String fen) {
        Fen = fen;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

