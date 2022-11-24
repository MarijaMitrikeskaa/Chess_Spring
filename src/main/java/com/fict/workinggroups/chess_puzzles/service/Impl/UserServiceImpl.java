package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.entity.Role;
import com.fict.workinggroups.chess_puzzles.entity.User;
import com.fict.workinggroups.chess_puzzles.exception.InvalidFenException;
import com.fict.workinggroups.chess_puzzles.exception.InvalidUsernameOrPasswordException;
import com.fict.workinggroups.chess_puzzles.exception.PasswordsDoNotMatchException;
import com.fict.workinggroups.chess_puzzles.exception.UsernameAlreadyExistsException;
import com.fict.workinggroups.chess_puzzles.repository.UserRepository;
import com.fict.workinggroups.chess_puzzles.service.UserService;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }
    public User saveGuest(User guest)
    {
        if(!guest.getUsername().isEmpty())
        {
            return this.userRepository.save(guest);
        }
        else {
            throw new InvalidFenException();
        }
    }


    @Override
    public User register(String username, String password, String repeatPassword, Role role) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username,passwordEncoder.encode(password),role);
        return userRepository.save(user);
    }


        public Optional<User> getGuest(String  id) {
       return userRepository.findByUserId(id);
    }
}

