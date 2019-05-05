package com.hazalabs.englazyhaza;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import static android.support.v4.app.NotificationCompat.PRIORITY_HIGH;
import static android.support.v4.app.NotificationCompat.PRIORITY_LOW;

public class settings extends AppCompatActivity {
    public NotificationManager notificationManager;
    private static final int ID_NOTIFY = 1;
    private static final String ID_CHANEL = "CHANEL_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Switch notiOn = (Switch)findViewById(R.id.switch1);
        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notiOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent intent = new Intent(getApplicationContext(), settings.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(getApplicationContext(), ID_CHANEL)
                            .setAutoCancel(false)
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setContentTitle("Notification")
                            .setContentText("SetText")
                            .setPriority(PRIORITY_HIGH);
                if(isChecked){
                    createChannelIfNeeded(notificationManager);
                    notificationManager.notify(ID_NOTIFY, notificationBuilder.build());
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
}
