package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.entity.User;
import com.fict.workinggroups.chess_puzzles.exception.InvalidArgumentsException;
import com.fict.workinggroups.chess_puzzles.exception.InvalidUserCredentialsException;
import com.fict.workinggroups.chess_puzzles.repository.UserRepository;
import com.fict.workinggroups.chess_puzzles.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}

