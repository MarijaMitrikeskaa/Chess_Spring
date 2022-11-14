package com.fict.workinggroups.chess_puzzles.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity

public class FenModel{


    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

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


    public String getId() {
        return id;
    }



    public void setId(String id) {
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

