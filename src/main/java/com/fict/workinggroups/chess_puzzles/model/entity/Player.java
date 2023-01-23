package com.fict.workinggroups.chess_puzzles.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


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


    public void setUserId(User userId) {
        this.userId = userId;
    }
}
