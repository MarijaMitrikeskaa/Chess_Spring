package com.fict.workinggroups.chess_puzzles.model.entity;

import com.fict.workinggroups.chess_puzzles.model.enums.Status;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Fens")
public class Fen {


    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "fen")
    private String fen;

    @Column(name = "description")
    private String description;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    public Fen(String fen, String description) {
        this.fen = fen;
        this.description = description;
        this.status=Status.PENDING;


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

