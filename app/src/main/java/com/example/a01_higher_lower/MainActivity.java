package com.example.a01_higher_lower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private int score = 0;

    private int highScore = 0;


    private int valueCurrent = 0;

    private int valuePrevious = 0;


    private TextView mScore;

    private TextView mHighScore;

    private TextView mLastThrow;

    private ImageView mDice;

    private Button mLower;

    private Button mRestart;

    private Button mHigher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // All components initialized.

        mDice = findViewById(R.id.imageViewDice);

        mLower = findViewById(R.id.buttonLower);

        mRestart = findViewById(R.id.buttonReset);

        mHigher = findViewById(R.id.buttonHigher);

        mLastThrow = findViewById(R.id.textViewLastThrow);

        mHighScore = findViewById(R.id.textViewHighScore);

        mScore = findViewById(R.id.textViewScore);

        rollDice();
        updateUI();


        // Click Listeners for the Buttons defined.

        //Click listener for buttonLower
        mLower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkInput(false);

            }
        });


        //Click listener for buttonHigher
        mHigher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkInput(true);

            }
        });

        //Click listener for buttonReset
        mRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reset();

            }
        });


    }


    private void rollDice() {

        valuePrevious = valueCurrent;

        valueCurrent = new Random().nextInt(6) + 1;


        switch (valueCurrent) {

            case 1:
                mDice.setImageDrawable(getDrawable(R.drawable.dice1));

                break;

            case 2:

                mDice.setImageDrawable(getDrawable(R.drawable.dice2));

                break;

            case 3:

                mDice.setImageDrawable(getDrawable(R.drawable.dice3));

                break;

            case 4:

                mDice.setImageDrawable(getDrawable(R.drawable.dice4));

                break;

            case 5:

                mDice.setImageDrawable(getDrawable(R.drawable.dice5));

                break;

            case 6:

                mDice.setImageDrawable(getDrawable(R.drawable.dice6));

                break;

        }

    }


    private void checkInput(boolean chooseHigher) {

        rollDice();

        if (valueCurrent == valuePrevious) {

            throwAgain();

        } else if (chooseHigher && valueCurrent > valuePrevious) {

            answerCorrect();

        } else if (chooseHigher && !(valueCurrent > valuePrevious)) {

            answerIncorrect();

        } else if (!chooseHigher && valueCurrent < valuePrevious) {

            answerCorrect();

        } else if (!chooseHigher && !(valueCurrent < valuePrevious)) {

            answerIncorrect();

        }

        updateUI();

    }


    private void throwAgain() {

        Toast.makeText(this, "Throw again...", Toast.LENGTH_SHORT).show();

    }


    private void answerIncorrect() {

        Toast.makeText(this, "Game over", Toast.LENGTH_SHORT).show();

        if (score > highScore) {

            highScore = score;

        }

        score = 0;

    }


    private void answerCorrect() {

        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();

        score++;

    }

    private void updateUI() {

        mScore.setText("Score: " + score);

        mHighScore.setText("HighScore: " + highScore);

        mLastThrow.setText("Last throw: " + valuePrevious);

    }


    private void reset() {

        score = 0;

        highScore = 0;

        valuePrevious = 0;

        updateUI();

    }


}
