package com.example.tictactoe;

public class Player {
    private int state = 0;
    private int score = 0;


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
