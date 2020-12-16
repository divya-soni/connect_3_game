package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    //0: yellow, 1: red, 2: empty
    int[] gameCheck = {2,2,2,2,2,2,2,2,2};
    int[][] winningStates = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    boolean gameActive = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn (View view) {
       if (gameActive == true) {
            ImageView img = (ImageView) view;
            int tag = Integer.parseInt(img.getTag().toString());
            Log.i("Tag", img.getTag().toString());
            if (gameCheck[tag] == 2) {
                img.setTranslationY(-1500);
                img.animate().translationYBy(1500).setDuration(500);
                if (activePlayer == 0) {
                    img.setImageResource(R.drawable.yellow_chip);
                    activePlayer = 1;
                    gameCheck[tag] = 0;
                } else {
                    img.setImageResource(R.drawable.red_chip);
                    activePlayer = 0;
                    gameCheck[tag] = 1;
                }
            }
            for (int[] winningState : winningStates) {
                if (gameCheck[winningState[0]] == gameCheck[winningState[1]] && gameCheck[winningState[1]] == gameCheck[winningState[2]] && gameCheck[winningState[0]] != 2) {
                    //Someone has won!
                    String winner;
                    if (activePlayer == 1) {
                      //  Toast.makeText(this, "Yellow has won!", Toast.LENGTH_SHORT).show();
                        winner = "Yellow";
                    }
                    else {
                       // Toast.makeText(this, "Red has won!", Toast.LENGTH_SHORT).show();
                        winner = "Red";
                    }
                    gameActive = false;

                    Button button = (Button) findViewById(R.id.button);
                    TextView text = (TextView) findViewById(R.id.textView);
                    text.setText(winner + " has won!");
                    button.setVisibility(View.VISIBLE);
                    text.setVisibility(View.VISIBLE);



                }
            }
        }


    }

    public void playAgain (View view)
    {
        Button button;
        button = (Button) findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);

        TextView text = (TextView) findViewById(R.id.textView);
        text.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout grid = findViewById(R.id.gridLayout);

       for(int i = 0; i < grid.getChildCount(); i++){
          ImageView token = (ImageView) grid.getChildAt(i);
           token.setImageDrawable(null);
      }

        for(int i = 0; i<9; i++) {
            gameCheck[i] = 2;
        }
        activePlayer = 0;

        gameActive = true;

    }

}