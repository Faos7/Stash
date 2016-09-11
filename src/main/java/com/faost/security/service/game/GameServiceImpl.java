package com.faost.security.service.game;

import com.faost.security.domain.Game;
import com.faost.security.domain.User;
import com.faost.security.logic.GameLogic;
import com.faost.security.repository.GameRepository;
import com.faost.security.service.misc.DBService;
import com.faost.security.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by faos7 on 28.12.16.
 */
@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;
    private UserService userService;
    private DBService dbService;

    @Autowired
    public GameServiceImpl(UserService userService, DBService dbService, GameRepository gameRepository) {
        this.userService = userService;
        this.dbService = dbService;
        this.gameRepository = gameRepository;
    }

    @Override
    public String letsPlay(User user){
        try {
            Game game;
            //проверяем занят ли Юзер
            if(!user.getBusy()){
                //если нет, находим ему занятие
                userService.setUserBusy(user);
                //получаем игру, здесь нам не важно есть ли уже созданная игра или нет
                //если она есть то Юзер становится вторым игроком, если нет то первым
                game = dbService.getGame(user.getId());
                user.setGame(game);
                game.addCurrentGameUser(user);
            }
            else {
                game = dbService.getActiveGame(user.getId());
            }
            if (dbService.isOpponentNotPresent(game.getId())){
                TimeUnit.SECONDS.sleep(5);
                letsPlay(user);
                return "waiting_for_oponent";
            }
            else {
                GameLogic.getInstance().playing(game, user);
                return "OK";
            }
        }catch (Exception ex){
                return "error";
        }
    }

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game getGameById(Long id) {
        return gameRepository.findOne(id);
    }
}
