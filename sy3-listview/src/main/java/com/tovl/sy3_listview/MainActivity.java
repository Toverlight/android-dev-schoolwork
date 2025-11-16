package com.tovl.sy3_listview;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

        EditText usernameEditText = dialogView.findViewById(R.id.et_username);
        EditText passwordEditText = dialogView.findViewById(R.id.et_password);
        Button cancelButton = dialogView.findViewById(R.id.btn_cancel);
        Button signInButton = dialogView.findViewById(R.id.btn_signin);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        cancelButton.setOnClickListener(v -> {
            Toast.makeText(this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
            alertDialog.dismiss(); // 关闭对话框
        });

        signInButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // 简单的登录逻辑示例
            if ("admin".equals(username) && "123456".equals(password)) {
                Toast.makeText(this, "登录成功!", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss(); // 登录成功后关闭对话框
            } else {
                Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog.show();
    }
}