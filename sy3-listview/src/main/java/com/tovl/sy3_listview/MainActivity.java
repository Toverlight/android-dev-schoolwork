package com.tovl.sy3_listview;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_sa = findViewById(R.id.btn_activity_sa);
        Button btn_ad = findViewById(R.id.btn_activity_ad);
        Button btn_xml = findViewById(R.id.btn_activity_xml);
        Button btn_am = findViewById(R.id.btn_activity_am);

        btn_sa.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SimpleActivity.class));
        });
        btn_ad.setOnClickListener(v -> {
            showCustomAlertDialog();
        });
        btn_xml.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MenuActivity.class));
        });
        btn_am.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ActionModeActivity.class));
        });

    }

    private void showCustomAlertDialog() {
        LayoutInflater inflater = getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.layout_alert_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(dialogView);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}