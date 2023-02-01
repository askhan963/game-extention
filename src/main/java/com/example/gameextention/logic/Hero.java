package com.example.gameextention.logic;


import com.example.gameextention.Game;

public abstract class Hero {

    protected int posX;
    protected int posY;
    protected Game game;

    protected Hero(Game game) {
        this.posX = 0;
        this.posY = 0;
        this.game = game;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public abstract void useSpecialPower(char direction) throws IllegalMoveException;

}
