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

    public Map<String, Integer> playerX = new HashMap<String, Integer>() {{
        put("state", 0);
        put("score", 0);
    }};

    public Map<String, Integer> playerO = new HashMap<String, Integer>() {{
        put("state", 0);
        put("score", 0);
    }};

    public Game() {

    }

    public void toggleTurn() {
        if(turn == "x") {
            turn = "o";
        } else {
            turn = "x";
        }
        message = turn + " turn";
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
        Map player = (turn == "x") ? playerX : playerO;
        int state = (Integer)player.get("state");
        player.put("state", state + stateToAdd);
    }

    public void checkForWin() {
        boolean playerXHasWon = isWin(playerX.get("state"));
        boolean playerOHasWon = isWin(playerO.get("state"));

        if(playerXHasWon) {
            //increment x score
            int score = playerX.get("score");
            playerX.put("score", score + 1);
            //change message to x wins
            message = "X wins";
        } else if (playerOHasWon) {
            //increment o score
            int score = playerO.get("score");
            playerO.put("score", score + 1);
            //change message to o wins
            message = "O wins";
        } else if (numMovesLeft == 0) {
            //disable rest of board
            //change message to tie
            message = "Tie";
        }
    }
}
