package com.fict.workinggroups.chess_puzzles.service;


import com.fict.workinggroups.chess_puzzles.entity.User;

public interface AuthService {
    User login(String username, String password);
}
