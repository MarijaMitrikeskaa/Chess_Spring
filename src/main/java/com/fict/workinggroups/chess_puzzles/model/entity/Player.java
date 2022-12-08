package com.fict.workinggroups.chess_puzzles.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Player {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User userId;

    private String username;

    @ManyToMany
    @JoinColumn(name = "fen_id")
    private Set<Fen> fens;

    @ManyToMany
    private Set<Tournament> tournaments;

    //private int NumOfTournaments;

    public Player() {
    }



    public Player(String username) {

        this.username=username;


    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public User getUserId() {
        return userId;
    }

    public Set<Fen> getFens() {
        return fens;
    }

    public Set<Tournament> getTournaments() {
        return tournaments;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
