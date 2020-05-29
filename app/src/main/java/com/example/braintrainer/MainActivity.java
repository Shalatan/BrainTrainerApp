package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView scoreTextView;
    TextView sumTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView timerTextView;
    SeekBar timerSeekBar;
    int locationOfCorrectAnswer;
    TextView setGameTimeTextView;
    long gameTime;
    TextView resultTextView;
    int score = 0;
    int numberOfQuestion = 0;
    ConstraintLayout gameLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    public  void playAgain(View view)
    {
        score = 0;
        timerSeekBar.setVisibility(View.INVISIBLE);
        setGameTimeTextView.setVisibility(View.INVISIBLE);
        numberOfQuestion = 0;
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        timerTextView.setText(" ");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        new CountDownTimer(gameTime, 1000)
        {
            @Override
            public void onTick(long gameTime) {
                timerTextView.setText(String.valueOf(gameTime/1000) + "s");
            }
            @Override
            public void onFinish() {
                resultTextView.setText("DONE !!");
                playAgainButton.setVisibility(View.VISIBLE);
                timerSeekBar.setVisibility(View.VISIBLE);
                setGameTimeTextView.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
    }

    public  void chosseAnswer(View view)
    {
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString()))
        {
            resultTextView.setText("CORRECT :)");
            score++;
        }
        else
        {
            resultTextView.setText("WRONG :( ");
        }
        numberOfQuestion++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
        newQuestion();
    }

    public void start(View view)
    {
        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.scoreTextView));
        gameLayout.setVisibility(View.VISIBLE);
    }

    public void newQuestion()
    {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for(int i=0;i<4;i++)
        {
            if(i == locationOfCorrectAnswer)
            {
                answers.add(a+b);
            }
            else
            {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == a+b)
                {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString((answers.get(0))));
        button1.setText(Integer.toString((answers.get(1))));
        button2.setText(Integer.toString((answers.get(2))));
        button3.setText(Integer.toString((answers.get(3))));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        button0 = findViewById(R.id.button0);
        setGameTimeTextView = findViewById(R.id.setGameTimeTextView);
        timerSeekBar = findViewById(R.id.timerSeekBar);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.playAgainButton);
        timerTextView = findViewById(R.id.timerTextView);
        gameLayout = findViewById(R.id.gameLayout);
        playAgain(findViewById(R.id.button0));
        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

        timerSeekBar.setMax(30000);
        timerSeekBar.setProgress(20000);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                gameTime = (long) i;
                if(gameTime < 5000)
                    gameTime = 5000;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }
}
