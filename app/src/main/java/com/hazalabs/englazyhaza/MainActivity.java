package com.hazalabs.englazyhaza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.action_settings:
                //go on next activity
                return true;
            case R.id.action_about:
                //go on next activity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //testcodeand
        final int counter = 0;
        final EditText TextTest = (EditText)findViewById(R.id.editText);
        final TextView ChangeClipBoard = (TextView)findViewById(R.id.textView2);
        final TextView counterText = (TextView)findViewById(R.id.textView);
        Button translate = (Button)findViewById(R.id.button);
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
                    InputStream vacab = getResources().openRawResource(R.raw.testword);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(vacab));
                    //scanner = new Scanner(reader);
                    //defendLine = reader.readLine();//while(scanner.hasNextLine()) {
                        while ((defendLine = reader.readLine()) != null) {
                            //String line = scanner.nextLine();
                            //String[] cols = line.split("--");
                            String line = defendLine.toLowerCase();
                            String[] cols = line.split("--");
                            if (cols[1].equals(word)) {
                                ChangeClipBoard.setText(cols[2]);
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
                    ChangeClipBoard.setText("Exception");
                }
                finally {

                }
            }

        });
    }
}
