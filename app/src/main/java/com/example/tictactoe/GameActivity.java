package com.example.tictactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class GameActivity extends AppCompatActivity {
    private Game game = new Game();
    TextView message;
    TextView xScoreSet1;
    TextView oScoreSet1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        message = findViewById(R.id.message);
        xScoreSet1 = findViewById(R.id.x_score_set1);
        oScoreSet1 = findViewById(R.id.o_score_set1);
    }

    public void squareClickListener(View view) {

        updateSquare(view);
        int state = getSquareState(view);
        game.updatePlayerState(game.turn, state);
        game.numMovesLeft -= 1;
        game.checkForWin();
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
        button.setTextColor(Color.DKGRAY);
    }

    public int getSquareState(View view) {
        int id = view.getId();
        String idString = getResources().getResourceEntryName(id);
        int exponent = Character.getNumericValue(idString.charAt(idString.length() - 1));
        int state = (int)Math.pow(2, exponent);
        return state;
    }

    public void updateScore(Game game) {
        int xScore = game.playerX.get("score");
        xScoreSet1.setText(xScore);

        int oScore = game.playerO.get("score");
        oScoreSet1.setText(oScore);
    }

    public void resetClickListener(View view) {
        //cleanup player states and scores
        //cleanup scores
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

}
