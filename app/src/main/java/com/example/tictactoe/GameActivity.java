package com.example.tictactoe;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class GameActivity extends AppCompatActivity {
    private String turn = "x";
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        message = findViewById(R.id.message);
    }

    public void squareClickListener(View view) {
        int id = view.getId();
        Button button = (Button)view;

        //update the text to the current player's turn
        button.setText(turn);

        //disable button from being clicked again
        button.setEnabled(false);
        button.setTextColor(Color.DKGRAY);
        //check if the player has won

        //if the player has won
        // update their score, set message
        //otherwise
        //toggle whose turn it is, set message
        toggleTurn();
        displayMessage(turn.toUpperCase() + " turn");
        //decrement the number of moves
    }

    private void toggleTurn() {
        if(turn == "x") {
            turn = "o";
        } else {
            turn = "x";
        }
    }

    private void displayMessage(String text) {
        message.setText(text);
    }
}
