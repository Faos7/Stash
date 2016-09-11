package com.faost.security.service.game;

import com.faost.security.domain.Game;
import com.faost.security.domain.User;

/**
 * Created by faos7 on 28.12.16.
 */
public interface GameService {
    String letsPlay(User user);

    Game save(Game game);

    Game getGameById(Long id);
}
