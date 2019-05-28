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
import java.util.Random;

public class tossup extends AppCompatActivity {
    int TLint;
    int FLint;
    TextView CurrentWord;
    TextView ShowWord;
    boolean isClick = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent Mainintent = getIntent();
        String FL = Mainintent.getStringExtra("FL");
        String TL = Mainintent.getStringExtra("TL");
        FLint = Integer.parseInt(FL);
        TLint = Integer.parseInt(TL);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tossup);
        CurrentWord = (TextView)findViewById(R.id.textView13);
        ShowWord = (TextView)findViewById(R.id.textView15);
        final Button showText = (Button)findViewById(R.id.button8);
        showText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isClick == false) {
                    StartTossUp();
                    showText.setText("Show");
                    ShowWord.setVisibility(View.INVISIBLE);
                    isClick = true;
                }
                else {
                    isClick = false;
                    showText.setText("next");
                    ShowWord.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void StartTossUp() {
        String defendLine;
        try {
            InputStream vacab = getResources().openRawResource(R.raw.vocabulary);
            BufferedReader reader = new BufferedReader(new InputStreamReader(vacab));
            Random rnd = new Random(System.currentTimeMillis());
            int number = 1 + rnd.nextInt(211 - 1 + 1);
            int i = 1;
            defendLine = reader.readLine();
            while (number != i){
                defendLine = reader.readLine();
                i++;
            }
            String[] cols = defendLine.split("--");
            CurrentWord.setText(cols[FLint]);
            ShowWord.setText(cols[TLint]);
            reader.reset();
        } catch (IOException ex) {

        } catch (NullPointerException exe) {

        }
    }
}
