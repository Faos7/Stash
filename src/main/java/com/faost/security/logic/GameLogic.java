package com.faost.security.logic;


import com.faost.security.domain.*;
import com.faost.security.repository.GameRepository;
import com.faost.security.repository.UserRepository;
import com.faost.security.service.misc.ConsoleHelper;
import com.faost.security.service.misc.DBService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * Created by faos7 on 29.12.16.
 */
public class GameLogic {

    public static GameLogic instance;

    public static synchronized GameLogic getInstance(){
        if (instance == null){
            instance = new GameLogic();
        }
        return instance;
    }

    @Autowired
    DBService dbService;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    UserRepository userRepository;

    public void playing(Game game, User user)throws InterruptedException{
        //обновляем игру, актуально при повторном входе
        Game updGame = gameRepository.findOne(game.getId());
        //проверяем не проиграл ли игрок:
        //если последний ход соперника был победным,
        //то игра имеет статус "GAME_OVER"
        //получаем поле в виде массива
        int [] canvas = decrypt(updGame.getField());
        //проверяем наличие свободных ячеек
        if (updGame.getCurrent().equals(CurrentPlayer.D)){
            updGame.setStatus(GameStatus.GAME_OVER);
            dbService.saveResultsOfGame(user, GameResult.DRAW);
            //сохраняем все изменения на поле
            gameRepository.save(game);
            user.setGame(null);
            updGame.setCurrentgameUsers(null);
            gameRepository.save(updGame);
            userRepository.save(user);
            return;
        }
        if (updGame.getStatus().equals(GameStatus.GAME_OVER)){
            //обновляем и сохраняем статистику игрока
            dbService.saveResultsOfGame(user, GameResult.LOSER);
            user.setGame(null);
            updGame.setCurrentgameUsers(null);
            gameRepository.save(updGame);
            userRepository.save(user);
            return;
        }
        //ура-ура игрок не проиграл!
        //проверяем чей ход:
        if ((   updGame.getX().equals(user.getId())
                && updGame.getCurrent().equals(CurrentPlayer.X))
                || (updGame.getY().equals(user.getId()))
                && updGame.getCurrent().equals(CurrentPlayer.Y)){
            //если право хода у игрока
            int n = ConsoleHelper.getNumber(canvas);
            canvas[n] = updGame.getX().equals(user.getId()) ? 1 : 2;
            //обновляем поле игры
            updGame.setField(crypt(canvas));
            //сохраняем все изменения на поле
            //проверяем был ли ход победным или ничья
            if (isGameOver(n, canvas)) {
                //меняем значение статуса игры на GAME_OVER, сохраним в конце хода
                updGame.setStatus(GameStatus.GAME_OVER);
            }else  if(isDraw(canvas)){
                updGame.setCurrent(CurrentPlayer.D);
            } else {
                //если ход не был победным
                //меняем значение CurrentPlayer на противоположное
                if (updGame.getCurrent().equals(CurrentPlayer.Y))
                    updGame.setCurrent(CurrentPlayer.X);
                else updGame.setCurrent(CurrentPlayer.Y);
            }
            //сохраняем все изменения на поле
            gameRepository.save(updGame);
            if (updGame.getStatus().equals(GameStatus.GAME_OVER)){
                //обновляем и сохраняем статистику игрока
                dbService.saveResultsOfGame(user, GameResult.WINNER);
                user.setGame(null);
                updGame.setCurrentgameUsers(null);
                gameRepository.save(updGame);
                userRepository.save(user);
                return;
            }
        }else {
            //если право хода у соперника
            TimeUnit.SECONDS.sleep(5);
        }
        //запускаем рекурсию
        playing(game, user);
        /**
         * если игрок сейчас делал ход, то происходит смена хода: CurrentPlayer меняя свое
         * значение, меняет так же и порядок ветвления в методе. Если игрок был в ожидании
         * хода противника, то всё зависит от того, успел ли противник сделать ход.
         * Если проитвник не успел, то повторяется ожидание.
         * Если успел, то CurrentPlayer в БД изменяет свое значение и в следующей итерации
         * рекурсии игрока метод предлагает уже игроку сделать ход.
         * У противника в свою очередь метод так же подходит к рекурсивному вызову,
         * но в следующей итерации он натыкается на ожидание хода игрока.
         */
    }


    //получаем массив из инта
    static Integer crypt(int[] canvas){
        int res = 0;
        for (int i = 0; i < 9; i++) {
            res = res * 10 + canvas[i];
        }
        return res;
    }

    static int [] decrypt(int n){
        int [] res = new int[9];
        for (int i = 8; i >-1; i--) {
            res[i] = n%10;
            n /= 10;
        }
        return res;
    }

    //проверяем есть ли свободные ячейки
    static boolean isDraw(int [] canvas){
        for (int i : canvas){
            if (i==0) return false;
        }
        return true;
    }

    static boolean isGameOver(int n, int[] canvas){
        // 0 1 2
        // 3 4 5
        // 6 7 8
        //поиск совпадений по горизонтали
        int row = n-n%3; //номер строки - проверяем только её
        if (canvas[row]==canvas[row+1] &&
                canvas[row]==canvas[row+2]) return true;
        //поиск совпадений по вертикали
        int column = n%3; //номер столбца - проверяем только его
        if (canvas[column]==canvas[column+3])
            if (canvas[column]==canvas[column+6]) return true;
        //мы здесь, значит, первый поиск не положительного результата
        //если значение n находится на одной из граней - возвращаем false
        if (n%2!=0) return false;
        //проверяем принадлежит ли к левой диагонали значение
        if (n%4==0){
            //проверяем есть ли совпадения на левой диагонали
            if (canvas[0] == canvas[4] &&
                    canvas[0] == canvas[8]) return true;
            if (n!=4) return false;
        }
        return canvas[2] == canvas[4] &&
                canvas[2] == canvas[6];
    }
}
