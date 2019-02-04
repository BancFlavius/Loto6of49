package com.example.loto6din49;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    DatabaseMethods dbm = new DatabaseMethods();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button backBtn = findViewById(R.id.button6);
        Button insertTicket = findViewById(R.id.button7);

        final EditText fullname = findViewById(R.id.fullname);
        final EditText phone = findViewById(R.id.phone);
        final EditText num1 = findViewById(R.id.num1);
        final EditText num2 = findViewById(R.id.num2);
        final EditText num3 = findViewById(R.id.num3);
        final EditText num4 = findViewById(R.id.num4);
        final EditText num5 = findViewById(R.id.num5);
        final EditText num6 = findViewById(R.id.num6);


        backBtn.setOnClickListener(v -> startActivity(new Intent(Main2Activity.this, MainActivity.class)));

        insertTicket.setOnClickListener(v -> {
            if(!fullname.getText().toString().equals("") && !phone.getText().toString().equals("") && !num1.getText().toString().equals("") && !num2.getText().toString().equals("") && !num3.getText().toString().equals("") && !num4.getText().toString().equals("") && !num5.getText().toString().equals("") && !num6.getText().toString().equals("")){
                dbm.insertTicket(fullname.getText().toString(), phone.getText().toString(), Integer.parseInt(num1.getText().toString()), Integer.parseInt(num2.getText().toString()), Integer.parseInt(num3.getText().toString()), Integer.parseInt(num4.getText().toString()), Integer.parseInt(num5.getText().toString()), Integer.parseInt(num6.getText().toString()) );
                Toast ticketInserted = Toast.makeText(getApplicationContext(), "Ticket Inserted", Toast.LENGTH_LONG);
                addNotification("Name: "+fullname.getText().toString() + " | Phone: " + phone.getText().toString() + " [ " +  Integer.parseInt(num1.getText().toString())+ " " +  Integer.parseInt(num2.getText().toString())+ " " +  Integer.parseInt(num3.getText().toString())+ " " +  Integer.parseInt(num4.getText().toString())+ " " +  Integer.parseInt(num5.getText().toString())+ " " +  Integer.parseInt(num6.getText().toString()) +" ]");
                ticketInserted.show();
                startActivity(new Intent(Main2Activity.this, Main2Activity.class));
            }else {
                Toast ticketInserted = Toast.makeText(getApplicationContext(), "Please complete all the fields", Toast.LENGTH_LONG);
                ticketInserted.show();
            }

        });
    }


    private static String DEFAULT_CHANNEL_ID = "default_channel";
    private static String DEFAULT_CHANNEL_NAME = "Default";

    private void addNotification(String ticketInfo){

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel(mNotificationManager);

        //2.Build Notification with NotificationCompat.Builder
        Notification notification = new NotificationCompat.Builder(this, DEFAULT_CHANNEL_ID)
                .setContentTitle("New Ticket Has Been Inserted")   //Set the title of Notification
                .setContentText(ticketInfo)    //Set the text for notification
                .setSmallIcon(R.drawable.lottery)   //Set the icon
                .build();

        //Send the notification.
        mNotificationManager.notify(1, notification);
    }


    /*
     * Create NotificationChannel as required from Android 8.0 (Oreo)
     * */
    public static void createNotificationChannel(NotificationManager notificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Create channel only if it is not already created
            if (notificationManager.getNotificationChannel(DEFAULT_CHANNEL_ID) == null) {
                notificationManager.createNotificationChannel(new NotificationChannel(
                        DEFAULT_CHANNEL_ID, DEFAULT_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
                ));
            }
        }
    }

}
