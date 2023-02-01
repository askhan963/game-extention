package com.example.gameextention.logic;

import com.example.gameextention.Game;


public class Hunter extends Hero {


    public Hunter(Game game) {
        super(game);
    }

    /**
     * @oracleIgnore
     */
    public void hunt(int setX, int setY) {
        game.getGameBoard().set(setX, setY, '_');
        for (Monster m : game.getGameBoard().getMonsters()) {
            if (m.getPosX() == setX && m.getPosY() == setY) {
                game.getGameBoard().getMonsters().remove(m);
                break;
            }
        }
        game.getGameBoard().generateMonster();
    }

    @Override
    public void useSpecialPower(char option) throws IllegalMoveException {
        boolean up = game.getGameBoard().get(posX, posY - 1) == 'M';
        boolean down = game.getGameBoard().get(posX, posY + 1) == 'M';
        boolean left = game.getGameBoard().get(posX - 1, posY) == 'M';
        boolean right = game.getGameBoard().get(posX + 1, posY) == 'M';

        if (!up && !down && !left && !right) {
            throw new IllegalMoveException("IllegalMoveException: You can only use your special power when you're adjacent to a monster");
        }
        switch (option) {
            case 'U':
                if (up) {
                    hunt(posX, posY - 1);
                } else {
                    throw new IllegalMoveException("IllegalMoveException: You can only use your special power when you're adjacent to a monster, but there is no monster above you!");
                }
                break;
            case 'D':
                if (down) {
                    hunt(posX, posY + 1);
                } else {
                    throw new IllegalMoveException("IllegalMoveException: You can only use your special power when you're adjacent to a monster, but there is no monster below you!");
                }
                break;
            case 'L':
                if (left) {
                    hunt(posX - 1, posY);
                } else {
                    throw new IllegalMoveException("IllegalMoveException: You can only use your special power when you're adjacent to a monster, but there is no monster on your left!");
                }
                break;
            case 'R':
                if (right) {
                    hunt(posX + 1, posY);
                } else {
                    throw new IllegalMoveException("IllegalMoveException: You can only use your special power when you're adjacent to a monster, but there is no monster on your right!");
                }
                break;
            default:
                break;
        }
    }
}
