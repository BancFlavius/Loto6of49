package com.example.loto6din49;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class GetAllTicketsActivity extends AppCompatActivity {

    DatabaseMethods dbm = new DatabaseMethods();
    Button getBtn, backBtn;
    TextView loadView, noTicket;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_tickets);

        getBtn = findViewById(R.id.getBtn3);
        backBtn = findViewById(R.id.backBtn3);
        loadView = findViewById(R.id.loadV2);
        noTicket = findViewById(R.id.noTicketBtn);
        ll = findViewById(R.id.linLayout);

        backBtn.setOnClickListener(v -> startActivity(new Intent(GetAllTicketsActivity.this, MainActivity.class)));

        getBtn.setOnClickListener(v -> {
            loadView.setText("LOADING");

            @SuppressLint("SetTextI18n") Thread thread = new Thread(() -> runOnUiThread(() -> {

                List<String> tickets = dbm.getAllTickets();
                StringBuilder stringTicket = new StringBuilder();
                int counter = 1;
                int sc = 10;
                boolean check = false;
                ll.removeAllViews();
                for(String s : tickets){

//                    if(counter == 4) {
//                        counter++;
//                        continue;
//                    }
                    stringTicket.append(s).append(" ");

                    if(counter <= 2) stringTicket.append("- ");

                    if(counter == 8) {
                        check = true;
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        params.setMargins(70,50,10,30);

                        TextView tv =  new TextView(GetAllTicketsActivity.this);

                        tv.setText(stringTicket.toString());
                        tv.setId(sc+counter);
                        tv.setTypeface(null, Typeface.BOLD);
                        tv.setLayoutParams(params);
                        tv.setTextSize(18);
                        ll.addView(tv);

                        counter = 0;
                        stringTicket = new StringBuilder();
                    }
                    counter++;
                    sc++;
                }

                if(!check){
                    noTicket.setText("There is no ticket in the DB");
                }

                loadView.setText("");
            }));
            thread.start();
        });
    }


}
