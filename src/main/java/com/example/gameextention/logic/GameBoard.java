package com.example.gameextention.logic;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    private final int sizeX;
    private final int sizeY;
    private char[][] boardMatrix;
    private final int maxMonsters;
    private List<Monster> monsters;

    public GameBoard(int sizeX, int sizeY) {
        monsters = new ArrayList<>();
        this.sizeX = Math.max(sizeX, 3);
        this.sizeY = Math.max(sizeY, 3);

        this.boardMatrix = new char[this.sizeX][this.sizeY];

        for (int i = 0; i < this.sizeY; i++) {
            for (int j = 0; j < this.sizeX; j++) {
                this.getBoardMatrix()[j][i] = '_';
            }
        }

        this.boardMatrix[0][0] = 'H';
        this.boardMatrix[this.sizeX - 1][this.sizeY - 1] = 'G';
        this.maxMonsters = (sizeX * sizeY) / 3;

        while (monsters.size() < maxMonsters) {
            this.generateMonster();
        }
    }

    public char get(int x, int y) {
        if (0 <= x && x < this.getSizeX() && 0 <= y && y < this.getSizeY()) {
            return boardMatrix[x][y];
        } else {
            return '\0';
        }
    }

    public void set(int x, int y, char piece) {
        if (0 <= x && x < getSizeX() && 0 <= y && y < getSizeY()) {
            boardMatrix[x][y] = piece;
        }
    }

    public void printGameBoard() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                builder.append(getBoardMatrix()[j][i]).append(" ");
            }
            builder.append("\n");
        }
        System.out.println(builder);
    }

    public int getSizeX() {
        return sizeX;
    }


    public int getSizeY() {
        return sizeY;
    }

    public char[][] getBoardMatrix() {
        return boardMatrix;
    }

    public void setBoardMatrix(char[][] boardMatrix) {
        this.boardMatrix = boardMatrix;
    }

    public void generateMonster() {
        if (monsters.size() >= maxMonsters) {
            return;
        }

        int xRandom = StudentRandom.randomInt(0, sizeX - 1);
        int yRandom = StudentRandom.randomInt(0, sizeY - 1);

        while (boardMatrix[xRandom][yRandom] != '_') {
            xRandom = StudentRandom.randomInt(0, sizeX - 1); //random number between 0 inclusive and sizeX exclusive
            yRandom = StudentRandom.randomInt(0, sizeY - 1);
        }

        this.boardMatrix[xRandom][yRandom] = 'M';
        monsters.add(new Monster(xRandom, yRandom));
    }


    public int getMaxMonsters() {
        return maxMonsters;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }
}
