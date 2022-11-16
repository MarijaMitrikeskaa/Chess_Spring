package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.entity.Guest;
import com.fict.workinggroups.chess_puzzles.exception.InvalidFenException;
import com.fict.workinggroups.chess_puzzles.repository.GuestRepository;
import com.fict.workinggroups.chess_puzzles.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Override
    public Optional<Guest> getGuestById(String id) {
//        if (!guestRepository.findById(id).isEmpty()) {
//            return this.guestRepository.findById(id);
//        }
        return null;
    }


    @Override
   public Guest saveGuest(Guest guest)
    {
     if(!guest.getName().isEmpty())
     {
         return this.guestRepository.save(guest);
     }
        else {
            throw new InvalidFenException();
     }
    }
}

