package com.hazalabs.englazyhaza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.util.Scanner;

public class QuizActivity extends AppCompatActivity {
    BufferedReader reader;
    boolean onClick = false;
    int TLint;
    int FLint;
    int FromCounter = 0;
    int ToCounter = 0;
    TextView ToLng;
    TextView FromLng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String FL = intent.getStringExtra("FL");
        String TL = intent.getStringExtra("TL");
        int idFire = Integer.parseInt(id); // для проверки какая нажата кнопка
        FLint = Integer.parseInt(FL);
        TLint = Integer.parseInt(TL);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        LearningActivity learningActivity = new LearningActivity();
        Button next = (Button)findViewById(R.id.button3);
        Button resetQuiz = (Button)findViewById(R.id.button4);
        final InputStream vacab = getResources().openRawResource(R.raw.vocabulary);
        reader = new BufferedReader(new InputStreamReader(vacab));
        switch (idFire){
            case (1):
                FromCounter = 1;
                ToCounter = 19;
                break;
            case (2):
                FromCounter = 20;
                ToCounter = 42;
                break;
            case (3):
                FromCounter = 43;
                ToCounter = 54;
                break;
            case (4):
                FromCounter = 55;
                ToCounter = 81;
                break;
            case (5):
                FromCounter = 82;
                ToCounter = 106;
                break;
            case (6):
                FromCounter = 107;
                ToCounter = 134;
                break;
            case (7):
                FromCounter = 135;
                ToCounter = 162;
                break;
            case (8):
                FromCounter = 163;
                ToCounter = 189;
                break;
            case (9):
                FromCounter = 190;
                ToCounter = 211;
                break;
        }
        resetQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Restart = getIntent();
                finish();
                startActivity(Restart);

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            StartQuiz(FromCounter,ToCounter);
            }
        });
    }
    public void StartQuiz(int FCounter, int TCounter){
        ToLng = (TextView)findViewById(R.id.textView9);
        FromLng = (TextView)findViewById(R.id.textView10);
        String defendLine;
        try{
                    defendLine = reader.readLine();
                    if(onClick == false) {
                        for (int i = 1; i < FCounter; i++) {
                            defendLine = reader.readLine();
                            onClick = true;
                        }
                    }
                    String[] cols = defendLine.split("--");
                    if(TCounter == Integer.parseInt(cols[0])){
                        Intent Restart = getIntent();
                        finish();
                        startActivity(Restart);
                        return;
                    }
                    ToLng.setText(cols[TLint]);
                    FromLng.setText(cols[FLint]);
        }
catch (IOException ex){

}
        catch (NullPointerException exe){
            ToLng.setText("If you want to repeat, click 'reset' ");
        }
finally {

        }
    }
}
