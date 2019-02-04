package com.example.loto6din49;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

public class WinningTicketsActivity extends AppCompatActivity {

    DatabaseMethods dbm = new DatabaseMethods();
    Button backBtn, getTicketsBtn;
    TextView noWin, loadginView, winNum;
    ScrollView scrView;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning_tickets);

        backBtn = findViewById(R.id.backBtn);
        getTicketsBtn = findViewById(R.id.getBtn);
        scrView = findViewById(R.id.scrlView);
        ll = findViewById(R.id.linLayout);
        noWin = findViewById(R.id.noWin);
        loadginView = findViewById(R.id.loadV);
        winNum =findViewById(R.id.winNumView);


        backBtn.setOnClickListener(v -> startActivity(new Intent(WinningTicketsActivity.this, MainActivity.class)));

        getTicketsBtn.setOnClickListener(v -> {
            loadginView.setText("LOADING");

            @SuppressLint("SetTextI18n") Thread thread = new Thread(() -> runOnUiThread(() -> {

            List<String> winningTickets = dbm.getWinningTicket();
            StringBuilder winningTicket = new StringBuilder();
            int counter = 1;
            int sc = 10;
            boolean check = false;
            ll.removeAllViews();
            for(String s : winningTickets){

                if(counter == 4) {
                    counter++;
                    continue;
                }
                winningTicket.append(s).append(" ");

                if(counter <= 3) winningTicket.append("- ");

                if(counter == 10) {
                    check = true;
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(50,50,10,30);

                    TextView tv =  new TextView(WinningTicketsActivity.this);

                    tv.setText(winningTicket.toString());
                    tv.setId(sc+counter);
                    tv.setTypeface(null, Typeface.BOLD);
                    tv.setLayoutParams(params);
                    tv.setTextSize(18);
                    ll.addView(tv);

                    counter = 0;
                    winningTicket = new StringBuilder();
                }
                counter++;
                sc++;
            }

            if(!check){
                noWin.setText("There was no winner");
            } else {
                StringBuilder winNumbers = new StringBuilder();
                winNumbers.append("Winning numbers are: ");
                for(int i: dbm.getWinningNumber()){
                    winNumbers.append(i +" ");
                }
                winNum.setText(winNumbers.toString());
            }

            loadginView.setText("");
            }));
            thread.start();
        });
    }
}
