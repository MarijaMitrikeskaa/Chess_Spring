package com.fict.workinggroups.chess_puzzles.service;

import com.fict.workinggroups.chess_puzzles.entity.Guest;

import java.util.Optional;

public interface GuestService {

    Optional<Guest> getGuestById(String id);

    Guest saveGuest(Guest guest);
}
