package com.hazalabs.englazyhaza;

import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static android.support.v4.app.NotificationCompat.PRIORITY_HIGH;
import static android.support.v4.app.NotificationCompat.PRIORITY_LOW;

public class settings extends AppCompatActivity {
    int TLint;
    int FLint;
    String TitleWord;
    String TextWord;
    public NotificationManager notificationManager;
    private static final int ID_NOTIFY = 1;
    private static final String ID_CHANEL = "CHANEL_ID";
    long startTime = 1*60*1000; // 2 min
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String FL = intent.getStringExtra("FL");
        String TL = intent.getStringExtra("TL");
        FLint = Integer.parseInt(FL);
        TLint = Integer.parseInt(TL);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Switch notiOn = (Switch)findViewById(R.id.switch1);
        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notiOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    StartTossUp();
                    EditText time = (EditText)findViewById(R.id.editText2);
                    String timeStrin = time.getText().toString();
                    int timeInt = Integer.parseInt(timeStrin);
                    Timer timerAsync = new Timer();
                    TimerTask timerTaskAsync = new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override public void run() {
                                    Intent intent = new Intent(getApplicationContext(), settings.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
                                    NotificationCompat.Builder notificationBuilder =
                                            new NotificationCompat.Builder(getApplicationContext(), ID_CHANEL)
                                                    .setAutoCancel(false)
                                                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                                                    .setWhen(System.currentTimeMillis())
                                                    .setContentIntent(pendingIntent)
                                                    .setContentTitle(TitleWord)
                                                    .setContentText(TextWord)
                                                    .setPriority(PRIORITY_HIGH);
                                    Toast toast = Toast.makeText(getApplicationContext(), "Push - up active!", Toast.LENGTH_SHORT);
                                    toast.show();
                                    createChannelIfNeeded(notificationManager);
                                    notificationManager.notify(ID_NOTIFY, notificationBuilder.build());
                                    Log.d("repeat","after each minite");
                                    //call web service here to repeat
                                }
                            });
                        }
                    };
                    timerAsync.schedule(timerTaskAsync, 0, timeInt * 60 * 1000);
                }
                else{

                }
            }
        });
    }
    public static void createChannelIfNeeded(NotificationManager manager){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(ID_CHANEL, ID_CHANEL,NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
        }
    }
    public void scheduleAlarm(View V)
    {
        // time at which alarm will be scheduled here alarm is scheduled at 1 day from current time,
        // we fetch  the current time in milliseconds and added 1 day time
        // i.e. 24*60*60*1000= 86,400,000   milliseconds in a day
        Long time = new GregorianCalendar().getTimeInMillis()+24*60*60*1000;

        // create an Intent and set the class which will execute when Alarm triggers, here we have
        // given AlarmReciever in the Intent, the onRecieve() method of this class will execute when
        // alarm triggers and
        //we call the method inside onRecieve() method pf Alarmreciever class
        Intent intentAlarm = new Intent(this, ReceiverAlarm.class);

        // create the object
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //set the alarm for particular time
        alarmManager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(this,1,  intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        Toast.makeText(this, "Alarm Scheduled for Tommrrow", Toast.LENGTH_LONG).show();

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
            TitleWord = cols[FLint];
            TextWord = cols[TLint];
            reader.reset();
        } catch (IOException ex) {

        } catch (NullPointerException exe) {

        }
    }

}
