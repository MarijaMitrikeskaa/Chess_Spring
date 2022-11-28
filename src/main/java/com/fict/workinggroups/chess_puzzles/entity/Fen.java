package com.fict.workinggroups.chess_puzzles.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Fens")
public class Fen {


    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "Fen")
    private String fen;

    @Column(name = "Description")
    private String description;

    public Fen(String fen, String description) {
        this.fen = fen;
        this.description = description;

    }



    public Fen() {

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

