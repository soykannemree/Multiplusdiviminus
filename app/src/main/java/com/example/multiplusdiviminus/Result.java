package com.example.multiplusdiviminus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView Point;
    Button playAgain;
    Button exit;
    int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Point=findViewById(R.id.textViewResult);
        playAgain=findViewById(R.id.buttonAgain);
        exit=findViewById(R.id.buttonEx);


        Intent intent=getIntent();
        score=intent.getIntExtra("score",0);
        Point.setText("Your Score : "+score);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Result.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}