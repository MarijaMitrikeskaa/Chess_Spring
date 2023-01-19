package com.fict.workinggroups.chess_puzzles.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Player {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User userId;

    private String username;

    @ManyToMany
    @JoinColumn(name = "fen_id")
    private Set<Fen> fens;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "players")
    private Set<Tournament> tournaments = new HashSet<>();
    private int points = 0;
    //private int NumOfTournaments;

    public Player() {
    }


    public Player(String username) {

        this.username = username;


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
