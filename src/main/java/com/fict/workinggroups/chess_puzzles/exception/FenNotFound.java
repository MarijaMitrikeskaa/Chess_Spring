package com.fict.workinggroups.chess_puzzles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class FenNotFound extends RuntimeException {

    public FenNotFound() {

        super("The requested FEN is not Found");
    }
}