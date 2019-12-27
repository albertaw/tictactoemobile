package com.example.tictactoe;

import java.util.HashMap;
import java.util.Map;

public class Game {
    public String turn = "x";
    /**
     *  273				 	84
     *   \				 	/
     *   1   |   2   |   4   | = 7
     * ------|-------|-------|
     *   8   |  16   |  32   | = 56
     * ------|-------|-------|
     *  64   |  128  |  256  | = 448
     * =======================
     *  73	   	 146	   292
     */
    private int[] winningStates = new int[]{7, 56, 448, 73, 146, 292, 273, 84};

    public int numMovesLeft = 9;

    public String message = "";

    public Player playerX = new Player();

    public Player playerO = new Player();

    public Game() {

    }

    public void toggleTurn() {
        if(turn == "x") {
            turn = "o";
        } else {
            turn = "x";
        }
    }


    /**
     *  Example:
     *  winning state = 7
     *  player state = 15
     *   0111
     * & 1111
     * ------
     *   0111
     *
     */
    public boolean isWin(int playerState) {
        for (int i = 0; i < winningStates.length; i++) {
            if ((winningStates[i] & playerState) == winningStates[i]) {
                return true;
            }
        }
        return false;
    }

    public void updatePlayerState(String turn, int stateToAdd) {
        Player player = (turn == "x") ? playerX : playerO;
        int state = player.getState();
        player.setState(state + stateToAdd);
    }

    public void checkForWin(GameActivity gameActivity) {
        boolean playerXHasWon = isWin(playerX.getState());
        boolean playerOHasWon = isWin(playerO.getState());

        if(playerXHasWon) {
            int score = playerX.getScore();
            playerX.setScore(score + 1);
            message = "X wins";
            gameActivity.disableBoard();
        } else if (playerOHasWon) {
            int score = playerO.getScore();
            playerO.setScore(score + 1);
            message = "O wins";
            gameActivity.disableBoard();
        } else if (numMovesLeft == 0) {
            message = "Tie";
        }
    }


}
