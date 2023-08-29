package com.example.multiplusdiviminus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class Sub extends AppCompatActivity {
    TextView scoreText;
    TextView score;
    TextView lifeText;
    TextView life;
    TextView timeText;
    TextView time;
    TextView question;
    EditText answer;
    Button enter;
    Button next;

    Random random =new Random();
    int randNum1;
    int randNum2;

    int answerUser;
    int answerRight;
    int scoreUser = 0;
    int lifeUser = 3;


    CountDownTimer timer;
    private static final long START_TIMER_IN_MILIS = 10000;
    Boolean timer_run;
    long time_left_milis=START_TIMER_IN_MILIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_div);
        scoreText = findViewById(R.id.textscore);
        score = findViewById(R.id.textViewscore);
        lifeText = findViewById(R.id.textlife);
        life = findViewById(R.id.textViewlife);
        timeText = findViewById(R.id.texttime);
        time = findViewById(R.id.textViewtime);
        question = findViewById(R.id.textViewquestion);
        answer = findViewById(R.id.editTextanswer);
        enter=findViewById(R.id.buttonenter);
        next=findViewById(R.id.buttonnext);

        next.setClickable(false);
        next.getBackground().setAlpha(128);
        transactionCont();


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerUser = Integer.valueOf(answer.getText().toString());
                pauseTime();

                answer.setVisibility(View.INVISIBLE);

                enter.setClickable(false);
                enter.getBackground().setAlpha(128);

                next.setClickable(true);
                next.getBackground().setAlpha(255);

                if (answerUser == answerRight){
                    scoreUser++;
                    score.setText(""+scoreUser);

                    question.setText("Congratulations, Right answer.");


                }
                else{
                    lifeUser--;
                    life.setText(""+lifeUser);
                    question.setText("Wrong answer.");

                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTime();

                answer.setVisibility(View.VISIBLE);

                enter.setClickable(true);
                enter.getBackground().setAlpha(255);

                next.setClickable(false);
                next.getBackground().setAlpha(128);

                answer.setText("");
                if (lifeUser>0){
                    transactionCont();}
                else{
                    Toast.makeText(getApplicationContext(),"Game Over",Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(getApplicationContext(), Result.class);
                    intent.putExtra("score",scoreUser);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }

    public void transactionCont(){
        randNum1 = random.nextInt(100);
        randNum2 = random.nextInt(10);
        answerRight = randNum1 - randNum2;

        question.setText(randNum1+"-"+randNum2);

        startTimer();
    }

    public void startTimer(){
        timer=new CountDownTimer(time_left_milis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_milis = millisUntilFinished;
                updateTime();
            }

            @Override
            public void onFinish() {
                timer_run = false;
                pauseTime();
                resetTime();
                updateTime();

                lifeUser--;
                life.setText(""+lifeUser);
                question.setText("Time is up.");

                next.setClickable(true);
                next.getBackground().setAlpha(255);

                enter.setClickable(false);
                enter.getBackground().setAlpha(128);
            }
        }.start();
        timer_run = true;
    }
    public void updateTime(){
        int second = (int)((time_left_milis/1000)%60);
        String time_left=String.format(Locale.getDefault(),"%02d",second);
        time.setText(time_left);

    }
    public void pauseTime(){
        timer.cancel();
        timer_run=false;
    }
    public void resetTime(){
        time_left_milis=START_TIMER_IN_MILIS;
        updateTime();

    }
}