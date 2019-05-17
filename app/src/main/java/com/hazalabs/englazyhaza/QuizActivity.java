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
import java.util.Scanner;

public class QuizActivity extends AppCompatActivity {
int checkedShit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        int idFire = Integer.parseInt(id); // для проверки какая нажата кнопка
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        LearningActivity learningActivity = new LearningActivity();
        TextView tx = (TextView)findViewById(R.id.textView9);
        Button next = (Button)findViewById(R.id.button3);
        tx.setText(id);
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
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            StartQuiz();
            }
        });
    }
    void StartQuiz(){
        TextView txs = (TextView)findViewById(R.id.textView10);
        String text;
        String defendLine;
        try{
            InputStream vacab = getResources().openRawResource(R.raw.testword);
            BufferedReader reader = new BufferedReader(new InputStreamReader(vacab));
            //Scanner scanner = new Scanner(reader);
            //text = scanner.nextLine();
            defendLine = reader.readLine();
            txs.setText(defendLine);
            //scanner.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
