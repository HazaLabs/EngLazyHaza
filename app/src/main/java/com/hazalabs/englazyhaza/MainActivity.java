package com.hazalabs.englazyhaza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
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
        Button translate = (Button)findViewById(R.id.button);
        final InputStream vacab = getResources().openRawResource(R.raw.testword);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(vacab));
        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0;i < 2;i++) {
                    FindWord();
                }

            }
            void FindWord(){
                try {
                    //InputStream vacab = getAssets().open("textword.txt");
                    Scanner scanner = new Scanner(reader.readLine());
                    String word = TextTest.getText().toString();
                    while(scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        String[] cols = line.split(" ");
                        if (cols[0].equals(word)) {
                            ChangeClipBoard.setText(cols[1]);
                        }
                        else {
                            ChangeClipBoard.setText("Слово отсутствует");
                        }
                    }
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
