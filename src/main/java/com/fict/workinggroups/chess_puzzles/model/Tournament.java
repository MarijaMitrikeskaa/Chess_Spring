package com.fict.workinggroups.chess_puzzles.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Tournament {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String name;

    private Date date;

    @ManyToMany
    private List<Player> players;

    public Tournament() {
    }

    public Tournament(String id) {
        this.id=id;
    }

    public String getId() {
        return id;
    }
}
