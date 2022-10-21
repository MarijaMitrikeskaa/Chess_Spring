package com.fict.workinggroups.chess_puzzles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException(Long id){
        super(String.format("Fen with id: %d is not found", id));


    }
}

