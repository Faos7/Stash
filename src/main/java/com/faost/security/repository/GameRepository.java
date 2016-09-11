package com.faost.security.repository;

import com.faost.security.domain.Game;

import com.faost.security.domain.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<Game> getGameByStatus(GameStatus status);

    List<Game> getGamesByX(Long x);

    List<Game> getGamesByY(Long y);
}
