package com.example.loto6din49;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseMethods dbm =  new DatabaseMethods();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button insertTicket = findViewById(R.id.button);
        Button dropTickets = findViewById(R.id.button4);
        Button dropWinningNumbers = findViewById(R.id.button5);
        Button getWinningNumbers = findViewById(R.id.button3);
        Button getWinningTickets = findViewById(R.id.button2);
        Button getAllTickets = findViewById(R.id.button13);

        insertTicket.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Main2Activity.class)));

        dropTickets.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SecureActivity.class)));

        dropWinningNumbers.setOnClickListener(v -> {
            dbm.dropWinningNumbers();
            dbm.insertWinningNumber(dbm.generateTicket());
            Toast dropWinningNumbersToast = Toast.makeText(getApplicationContext(), "Winning Numbers have been dropped", Toast.LENGTH_LONG);
            dropWinningNumbersToast.show();
        });

        getWinningNumbers.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, WinningNumbersActivity.class)));

        getWinningTickets.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, WinningTicketsActivity.class)));

        getAllTickets.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, GetAllTicketsActivity.class)));
    }
}
