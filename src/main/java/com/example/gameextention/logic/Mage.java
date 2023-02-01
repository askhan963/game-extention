package com.example.gameextention.logic;

import com.example.gameextention.Game;


//TODO:
public class Mage extends Hero {

    public Mage(Game game) {
        super(game);
    }

    /**
     * @oracleIgnore
     */
    public void teleport(int setX, int setY) {
        game.getGameBoard().set(posX, posY, '_');
        game.getHero().setPosX(setX);
        game.getHero().setPosY(setY);
        game.getGameBoard().set(posX, posY, 'H');
    }

    @Override
    public void useSpecialPower(char option) throws IllegalMoveException {
        boolean up = game.getGameBoard().get(posX, posY - 1) == '\0';
        boolean down = game.getGameBoard().get(posX, posY + 1) == '\0';
        boolean left = game.getGameBoard().get(posX - 1, posY) == '\0';
        boolean right = game.getGameBoard().get(posX + 1, posY) == '\0';

        if (!up && !down && !left && !right) {
            throw new IllegalMoveException("IllegalMoveException: You can only use your special power when you're at the edge of the map");
        }
        switch (option) {
            case 'U':
                if (up) {
                    teleport(posX, game.getGameBoard().getSizeY() - 1);
                } else {
                    throw new IllegalMoveException("IllegalMoveException: You can only use your special power when you're at the upper edge of the map");
                }
                break;
            case 'D':
                if (down) {
                    teleport(posX, 0);
                } else {
                    throw new IllegalMoveException("IllegalMoveException: You can only use your special power when you're at the bottom edge of the map");
                }
                break;
            case 'L':
                if (left) {
                    teleport(game.getGameBoard().getSizeX() - 1, posY);
                } else {
                    throw new IllegalMoveException("IllegalMoveException: You can only use your special power when you're at the leftmost edge of the map");
                }
                break;
            case 'R':
                if (right) {
                    teleport(0, posY);
                } else {
                    throw new IllegalMoveException("IllegalMoveException: You can only use your special power when you're at the rightmost edge of the map");
                }
                break;
            default:
                break;
        }
    }
}
