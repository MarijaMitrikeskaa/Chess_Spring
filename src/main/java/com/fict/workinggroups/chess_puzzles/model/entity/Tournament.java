package com.fict.workinggroups.chess_puzzles.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tournament {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;


    private String name;

    private LocalDate date = LocalDate.now();

    private boolean tournamentActive;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })

    //remove this
    @JoinTable(

            name = "tournament_player",
            joinColumns = {@JoinColumn(name = "tournament_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "player_id", referencedColumnName = "id")})
    private Set<Player> players = new HashSet<>();

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "tournament_fen",
            joinColumns = {@JoinColumn(name = "tournament_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "fen_id", referencedColumnName = "id")})
    private Set<Fen> fens = new HashSet<>();

    public Tournament() {
    }


    public Tournament(String name) {

        this.name = name;


    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public boolean isTournamentActive() {
        return tournamentActive;
    }

    public void setTournamentActive(boolean tournamentActive) {
        this.tournamentActive = tournamentActive;
    }

    public Set<Fen> getFens() {
        return fens;
    }

    public void setFens(Set<Fen> fens) {
        this.fens = fens;
    }
}