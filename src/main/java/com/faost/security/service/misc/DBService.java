package com.faost.security.service.misc;

import com.faost.security.domain.Game;
import com.faost.security.domain.GameResult;
import com.faost.security.domain.GameStatus;
import com.faost.security.domain.User;
import com.faost.security.repository.GameRepository;
import com.faost.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {

    private GameRepository gameRepository;
    private UserRepository userRepository;

    @Autowired
    public DBService(GameRepository gameRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    public Game getCreatedGame(){
        if (gameRepository.getGameByStatus(GameStatus.CREATED).isPresent()){
            return  gameRepository.getGameByStatus(GameStatus.CREATED).get();
        }
        else {
            return null;
        }
    }

    public Game getGame(Long n) {
        Game game = getCreatedGame();
        if (game == null){
            game = new Game(n);
            gameRepository.save(game);
        }else {
            game.setStatus(GameStatus.PLAYING);
            game.setY(n);
            gameRepository.save(game);
        }
        return game;
    }

    public Game getActiveGame(Long id){
        List<Game> games = gameRepository.getGamesByX(id);
        List<Game> tmp = gameRepository.getGamesByY(id);
        for (Game game: tmp) {
            games.add(game);
        }
        for (Game game : games){
            if (game.getStatus()==GameStatus.GAME_OVER){
                games.remove(game);
            }
        }
        if (games.size()!=0){
            return games.get(0);
        }
        else return null;
    }

    public boolean isOpponentNotPresent(Long n){
        Game updGame = gameRepository.findOne(n);
        return updGame.getX().equals(updGame.getY());
    }

    public void saveResultsOfGame(User user, GameResult result){
        user.setBusy(false);
        user.setGames(user.getGames()+1);
        if (result.equals(GameResult.LOSER)){
            user.setLoses(user.getLoses()+1);
        }
        if (result.equals(GameResult.WINNER)){
            user.setWins(user.getWins()+1);
        }
        userRepository.save(user);
    }
}
