package com.fict.workinggroups.chess_puzzles.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tournament {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;


    private String name;

    private LocalDate date = LocalDate.now();

    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(

            name = "tournament_player",
            joinColumns = {@JoinColumn(name = "tournament_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "player_id",referencedColumnName = "id")})
    private Set<Player> players=new HashSet<>();

    public Tournament() {
    }


    public Tournament(String name) {

        this.name=name;






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
}
