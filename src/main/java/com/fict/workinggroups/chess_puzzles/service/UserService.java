package com.fict.workinggroups.chess_puzzles.service;

import com.fict.workinggroups.chess_puzzles.entity.Role;
import com.fict.workinggroups.chess_puzzles.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword,Role role);
}


