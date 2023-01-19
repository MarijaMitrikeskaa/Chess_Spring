package com.fict.workinggroups.chess_puzzles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class InvalidFenException extends RuntimeException {

    public InvalidFenException() {

        super("InvalidFen");
    }
}
