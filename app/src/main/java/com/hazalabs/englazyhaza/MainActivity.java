package com.hazalabs.englazyhaza;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    public int FromLanguage = 0;
    public int ToLanguage = 0;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent sett = new Intent(this,settings.class);
        switch(id){
            case R.id.action_settings:
                startActivity(sett);
                return true;
            case R.id.action_about:
                //go on next activity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //testcodeand
        //String[] languages = {"Русский", "Український", "Ukrainian", "Polski", "Le français", "Español", "Česky"};
        final Spinner spinner = (Spinner)findViewById(R.id.Fromlang);
        final TextView counterText = (TextView)findViewById(R.id.textView);
        final Spinner spinner2 = (Spinner)findViewById(R.id.onlang);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this ,android.R.layout.simple_spinner_item, new String[]
                {"Русский", "Український", "English", "Polski", "Le français", "Español", "Česky"});
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapter);
        final Intent learnAct = new Intent(this,LearningActivity.class);
        final Intent tossupIntent = new Intent(MainActivity.this, tossup.class);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
                FromLanguage = spinner.getSelectedItemPosition() + 1;
                learnAct.putExtra("FL", Integer.toString(FromLanguage));
                tossupIntent.putExtra("FL", Integer.toString(FromLanguage));

            }
            public void onNothingSelected(AdapterView<?> parent) {
                FromLanguage = 1;
                learnAct.putExtra("FL", Integer.toString(FromLanguage));
                tossupIntent.putExtra("FL", Integer.toString(FromLanguage));

                //ForQuiz.putExtra("FL", Integer.toString(FromLanguage));
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
                ToLanguage = spinner2.getSelectedItemPosition() + 1;
                learnAct.putExtra("TL", Integer.toString(ToLanguage));
                tossupIntent.putExtra("TL", Integer.toString(ToLanguage));

                // ForQuiz.putExtra("TL", Integer.toString(ToLanguage));
            }
            public void onNothingSelected(AdapterView<?> parent) {
                ToLanguage = 1;
                learnAct.putExtra("TL", Integer.toString(ToLanguage));
                learnAct.putExtra("TL", Integer.toString(ToLanguage));

                //  ForQuiz.putExtra("TL", Integer.toString(ToLanguage));
            }
        });

        final Button tossup = (Button)findViewById(R.id.button6);
        tossup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(tossupIntent);
            }
        });
        final int counter = 0;
        final EditText TextTest = (EditText)findViewById(R.id.editText);
        final TextView ChangeClipBoard = (TextView)findViewById(R.id.textView2);
        Button translate = (Button)findViewById(R.id.button);
        Button learning = (Button)findViewById(R.id.button2);
        learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(learnAct);
            }
        });
        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    FindWord();

            }
            void FindWord(){
                //Scanner scanner;
                String word = TextTest.getText().toString();
                word = word.toLowerCase();
                try {
                    String defendLine;
                    int counter = 0;
                    InputStream vacab = getResources().openRawResource(R.raw.vocabulary);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(vacab));
                    //scanner = new Scanner(reader);
                    //defendLine = reader.readLine();//while(scanner.hasNextLine()) {
                        while ((defendLine = reader.readLine()) != null) {
                            //String line = scanner.nextLine();
                            //String[] cols = line.split("--");
                            String line = defendLine.toLowerCase();
                            String[] cols = line.split("--");
                            if (cols[FromLanguage].equals(word)) {
                                ChangeClipBoard.setText(cols[ToLanguage]);
                                break;
                            } else {
                                ChangeClipBoard.setText("Слово отсутствует");
                            }
                            counter = counter + 1;
                            counterText.setText(Integer.toString(counter));
                     //   }
                    }
                     reader.close();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                    ChangeClipBoard.setText("Exception,please,give feedback about error");
                }
                finally {

                }
            }

        });
    }
}
