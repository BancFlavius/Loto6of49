package com.example.loto6din49;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Set;

public class WinningNumbersActivity extends AppCompatActivity {

    DatabaseMethods dbm = new DatabaseMethods();
    TextView num1, num2, num3, num4, num5, num6;
    Button getBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning_numbers);
        num1 = findViewById(R.id.winNum1);
        num2 = findViewById(R.id.winNum2);
        num3 = findViewById(R.id.winNum3);
        num4 = findViewById(R.id.winNum4);
        num5 = findViewById(R.id.winNum5);
        num6 = findViewById(R.id.winNum6);
        getBtn = findViewById(R.id.getBtn2);
        backBtn = findViewById(R.id.backBtn2);


        Set<Integer> winningNumbers = dbm.getWinningNumber();

        backBtn.setOnClickListener(v -> startActivity(new Intent(WinningNumbersActivity.this, MainActivity.class)));

        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counter = 1;
                for(int i : winningNumbers){
                    switch (counter){
                        case 1:
                            System.out.println("INSIDE SWITCH STATEMENT I: " + i);
                            num1.setText(String.valueOf(i));
                            break;
                        case 2:
                            num2.setText(String.valueOf(i));
                            break;
                        case 3:
                            num3.setText(String.valueOf(i));
                            break;
                        case 4:
                            num4.setText(String.valueOf(i));
                            break;
                        case 5:
                            num5.setText(String.valueOf(i));
                            break;
                        case 6:
                            num6.setText(String.valueOf(i));
                            break;
                        default:
                            break;
                    }
                    counter++;
                }
            }
        });
    }
}
