package com.tovl.sy2_layout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnA = findViewById(R.id.btn_activity_linear);
        Button btnB = findViewById(R.id.btn_activity_table);
        Button btnC = findViewById(R.id.btn_activity_constraint1);
        Button btnD = findViewById(R.id.btn_activity_constraint2);

        btnA.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LinearActivity.class));
        });
        btnB.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TableActivity.class));
        });
        btnC.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Constraint1Activity.class));
        });
        btnD.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Constraint2Activity.class));
        });

    }
}