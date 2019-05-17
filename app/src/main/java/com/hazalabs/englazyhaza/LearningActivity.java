package com.hazalabs.englazyhaza;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class LearningActivity extends AppCompatActivity {
    int SelectTheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Button FirstTheme = (Button)findViewById(R.id.button11);
        Button SecondTheme = (Button)findViewById(R.id.button16);
        Button ThreeTheme = (Button)findViewById(R.id.button14);
        Button FourTheme = (Button)findViewById(R.id.button18);
        Button FiveTheme = (Button)findViewById(R.id.button5);
        Button SixTheme = (Button)findViewById(R.id.button15);
        Button SevenTheme = (Button)findViewById(R.id.button19);
        Button EightTheme = (Button)findViewById(R.id.button17);
        Button NineTheme = (Button)findViewById(R.id.button20);
        FirstTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectTheme = 1;
                CallQuiz();
            }
        });
        SecondTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectTheme = 2;
                CallQuiz();
            }
        });
        ThreeTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectTheme = 3;
                CallQuiz();
            }
        });
        FourTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectTheme = 4;
                CallQuiz();
            }
        });
        FiveTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectTheme = 5;
                CallQuiz();
            }
        });
        SixTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectTheme = 6;
                CallQuiz();
            }
        });
        SevenTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectTheme = 7;
                CallQuiz();
            }
        });
        EightTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectTheme = 8;
                CallQuiz();
            }
        });
        NineTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectTheme = 9;
                CallQuiz();
            }
        });
    }
    void CallQuiz(){
        Intent GoQuiz = new Intent(this, QuizActivity.class);
        GoQuiz.putExtra("id", Integer.toString(SelectTheme));
        startActivity(GoQuiz);
    }
}
