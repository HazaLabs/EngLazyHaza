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
    int TLint;
    int FLint;
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

                break;
            case (2):

                break;
            case (3):

                break;
            case (4):

                break;
            case (5):

                break;
            case (6):

                break;
            case (7):

                break;
            case (8):

                break;
            case (9):

                break;
        }
        resetQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ToLng.setText(" ");
                    FromLng.setText(" ");
                    try {
                        reader.reset();
                        vacab.reset();
                    }
                    catch (IOException ex){

                    }

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            StartQuiz();
            }
        });
    }
    public void StartQuiz(){
        ToLng = (TextView)findViewById(R.id.textView9);
        FromLng = (TextView)findViewById(R.id.textView10);
        try{
            String defendLine = reader.readLine();
            String[] cols = defendLine.split("--");
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
