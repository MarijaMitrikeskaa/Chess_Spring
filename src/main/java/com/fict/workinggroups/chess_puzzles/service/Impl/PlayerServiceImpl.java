package com.fict.workinggroups.chess_puzzles.service.Impl;

import com.fict.workinggroups.chess_puzzles.exception.InvalidUsernameException;
import com.fict.workinggroups.chess_puzzles.exception.PlayerNotFound;
import com.fict.workinggroups.chess_puzzles.model.dto.PlayerDto;
import com.fict.workinggroups.chess_puzzles.model.entity.Player;
import com.fict.workinggroups.chess_puzzles.model.entity.Tournament;
import com.fict.workinggroups.chess_puzzles.model.entity.User;
import com.fict.workinggroups.chess_puzzles.repository.PlayerRepository;
import com.fict.workinggroups.chess_puzzles.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Optional<Player> getPlayerById(String id) {
        return this.playerRepository.findById(id);
    }

    @Override
    public List<Player> getAllPlayers() {
        return this.playerRepository.findAll();
    }

    @Override
    public Optional<Player> deletePlayer(String id) {
        if (!playerRepository.findById(id).isEmpty()) {
            this.playerRepository.deleteById(id);
            return playerRepository.findById(id);
        } else {
            throw new PlayerNotFound();
        }

    }


    @Override
    public Optional<Player> editPlayer(String id, PlayerDto playerDto) {
        Player player = this.playerRepository.findById(id).orElseThrow(InvalidUsernameException::new);
// to check if the current username equals the one in the input
//        if (this.playerRepository.findByUsername(player.getUsername().equals(playerDto.getUsername()))){
//
//        }
        if (this.playerRepository.findByUsername(playerDto.getUsername()).isPresent()) {
            throw new InvalidUsernameException();
        }


        player.setUsername(playerDto.getUsername());
        return Optional.of(this.playerRepository.save(player));


    }

    @Override
    public boolean hasId(String playerId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();


        Player player = playerRepository.findById(playerId).get();

        String userId = user.getId();
        String idPlayer = player.getUserId().getId();
        return userId.equals(idPlayer);


    }


}
