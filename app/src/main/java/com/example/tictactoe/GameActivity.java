package com.example.tictactoe;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    private Game game = new Game();
    private TextView message;
    private TextView xScore;
    private TextView oScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        message = findViewById(R.id.message);
        xScore = findViewById(R.id.x_score);
        oScore = findViewById(R.id.o_score);
    }

    public void squareClickListener(View view) {
        updateSquare(view);
        int state = getSquareState(view);
        game.updatePlayerState(game.turn, state);
        game.numMovesLeft -= 1;

        game.checkForWin(this);

        updateScore(game);
        displayMessage(game.message);
        game.toggleTurn();
    }


    private void displayMessage(String text) {
        message.setText(text);
    }

    public void updateSquare(View view) {
        Button button = (Button)view;
        button.setText(game.turn);
        button.setEnabled(false);
        button.setTextColor(Color.WHITE);
    }

    public int getSquareState(View view) {
        int id = view.getId();
        String idString = getResources().getResourceEntryName(id);
        int exponent = Character.getNumericValue(idString.charAt(idString.length() - 1));
        int state = (int)Math.pow(2, exponent);
        return state;
    }

    public void updateScore(Game game) {
        int playerXScore = game.playerX.getScore();
        xScore.setText("" + playerXScore);

        int playerOScore = game.playerO.getScore();
        oScore.setText("" + playerOScore);
    }

    public void resetClickListener(View view) {
        //cleanup player states
        //cleanup scores?
        game.playerX.setState(0);
        game.playerO.setState(0);
        game.numMovesLeft = 9;
        cleanupMessage();
        cleanupBoard();
    }

    public void cleanupMessage() {
        game.message = "";
        displayMessage(game.message);
    }

    public void cleanupBoard() {
        for (int i = 0; i < 9; i++) {
            String squareId = "square_" + i;
            int id = getResources().getIdentifier(squareId, "id", "com.example.tictactoe");
            Button button = findViewById(id);
            button.setText("");
            button.setEnabled(true);
        }
    }

    public void disableBoard() {
        for (int i = 0; i < 9; i++) {
            String squareId = "square_" + i;
            int id = getResources().getIdentifier(squareId, "id", "com.example.tictactoe");
            Button button = findViewById(id);
            button.setEnabled(false);
        }
    }

}
