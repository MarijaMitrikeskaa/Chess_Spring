package com.fict.workinggroups.chess_puzzles.Entity;

import javax.persistence.*;

@Entity
@Table(name = "fens")
public class FenModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TheFens")
    private String fen;

    @Column(name = "TheDescription")
    private String description;



    public FenModel(String fen, String description) {
        this.fen = fen;
        this.description = description;
    }

    public FenModel() {

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFen() {
        return fen;
    }

    public void setFen(String fen) {
        this.fen = fen;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

