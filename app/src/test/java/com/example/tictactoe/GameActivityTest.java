package com.example.tictactoe;

import org.junit.Test;

import static org.junit.Assert.*;


public class GameActivityTest {
    @Test
    public void toggleTurn_changes_turn_to_o() {
        Game game = new Game();
        game.toggleTurn();
        assertEquals("o", game.turn);
    }

    @Test
    public void isWin_returns_true_when_three_squares_selected() {
        Game game = new Game();
        boolean isWin = game.isWin(7);
        assertTrue(isWin);
    }

    @Test
    public void isWin_returns_true_when_four_squares_selected() {
        Game game = new Game();
        boolean isWin = game.isWin(15);
        assertTrue(isWin);
    }

    @Test
    public void updates_playerX_state() {
        Game game = new Game();
        game.playerX.setState(4);
        game.updatePlayerState("x", 8);
        int state = game.playerX.getState();
        assertEquals(12, state);
    }
}
