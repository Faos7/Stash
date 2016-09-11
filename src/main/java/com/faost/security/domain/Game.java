package com.faost.security.domain;


import javax.persistence.*;
import java.util.List;

/**
 * Created by Faos7 on 17.04.2015.
 */

@Entity
@Table(name="game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "x")
    private Long x;

    @Column(name = "y")
    private Long y;

    @Column(name = "field")
    private Integer field;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private GameStatus status;

    @Column(name = "current")
    @Enumerated(EnumType.STRING)
    private CurrentPlayer current;

    @OneToMany(mappedBy = "game")
    private List<User> currentGameUsers;


    public Game() {
    }

    public Game(Long x) {
        this.x = x;
        this.y = x;
        this.field = 0;
        this.status = GameStatus.CREATED;
        this.current = CurrentPlayer.X;
    }


    public Long getId() {
        return id;
    }

    public Integer getField() {
        return field;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setField(Integer field) {
        this.field = field;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public CurrentPlayer getCurrent() {
        return current;
    }

    public void setCurrent(CurrentPlayer current) {
        this.current = current;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public List<User> getCurrentgameUsers() {
        return currentGameUsers;
    }

    public void setCurrentgameUsers(List<User> currentGameUsers) {
        this.currentGameUsers = currentGameUsers;
    }

    public void addCurrentGameUser(User user){
        currentGameUsers.add(user);
    }

    static int [] decrypt(int n){
        int [] res = new int[9];
        for (int i = 8; i >-1; i--) {
            res[i] = n%10;
            n /= 10;
        }
        return res;
    }
}
