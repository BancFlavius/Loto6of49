package com.example.loto6din49;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecureActivity extends AppCompatActivity {

    DatabaseMethods dbm =  new DatabaseMethods();
    Button noBtn, yesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secure);

        noBtn = findViewById(R.id.noBtn);
        yesBtn = findViewById(R.id.yesBtn);

        yesBtn.setOnClickListener(v -> {
            dbm.dropTicketTable();
            Toast dropTicketToast = Toast.makeText(getApplicationContext(), "Tickets Table has been dropped", Toast.LENGTH_LONG);
            dropTicketToast.show();
            startActivity(new Intent(SecureActivity.this, MainActivity.class));
        });

        noBtn.setOnClickListener(v -> startActivity(new Intent(SecureActivity.this, MainActivity.class)));

    }


}
