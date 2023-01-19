package com.fict.workinggroups.chess_puzzles.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fict.workinggroups.chess_puzzles.model.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "Fen")
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



    private String solution;
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "fens")
    private Set<Tournament> tournaments;

    public Fen(String fen, String description,String solution) {
        this.fen = fen;
        this.description = description;
        this.status=Status.PENDING;

        this.solution=solution;
    }

    public Fen(String fen, String description) {
        this.fen = fen;
        this.description = description;

        this.status=Status.PENDING;
    }





    public Fen() {

    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }





    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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