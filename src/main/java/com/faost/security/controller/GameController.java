package com.faost.security.controller;

import com.faost.security.service.game.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by faos7 on 29.12.16.
 */

@Controller
public class GameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
/*
    @RequestMapping("/game/{id}")
    public ModelAndView getGamePage(@PathVariable Long id) {
        LOGGER.debug("Getting game page for game={}", id);
        return new ModelAndView("current_game", "game", gameService.getGameById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));

    }*/
}
