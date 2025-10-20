package com.tovl.sy3_listview;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class SimpleActivity extends AppCompatActivity {

    List<Map<String, Object>> listItems;
    private static final String CHANNEL_ID = "animal_channel"; // 通知渠道ID

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // 用户授予了权限
                    Toast.makeText(this, "通知权限已授予", Toast.LENGTH_SHORT).show();
                } else {
                    // 用户拒绝了权限
                    Toast.makeText(this, "您拒绝了通知权限，将无法接收通知", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adapter);

        // 1. 创建通知渠道
        createNotificationChannel();
        // 2. 请求通知权限
        askNotificationPermission();

        ListView listView = findViewById(R.id.listView_sa);

        listItems = getMaps();

        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.simple_item_sa,
                new String[] { "textView_sa", "imageView_sa" },
                new int[] { R.id.textView_sa, R.id.imageView_sa });
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Map<String, Object> selectedItem = listItems.get(position);
            String selectedText = Objects.requireNonNull(selectedItem.get("textView_sa")).toString();
            Toast.makeText(
                    SimpleActivity.this,
                    selectedText,
                    Toast.LENGTH_SHORT
            ).show();

            // 3. 在点击时发送通知
            sendNotification(selectedText);
        });

    }

    @NonNull
    private static List<Map<String, Object>> getMaps() {
        List<Map<String, Object>> data = new ArrayList<>();

        String[] textArr = { "Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant" };
        int[] imageArr = { R.drawable.lion, R.drawable.tiger, R.drawable.monkey,
                R.drawable.dog, R.drawable.cat, R.drawable.elephant };

        for (int i = 0; i < textArr.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("textView_sa", textArr[i]);
            map.put("imageView_sa", imageArr[i]);
            data.add(map);
        }
        return data;
    }

    private void createNotificationChannel() {
        // Build.VERSION.SDK_INT >= Build.VERSION_CODES.O 表示 Android 8.0 或更高版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "动物通知"; // 渠道名称，会显示在系统设置中
            String description = "用于显示点击动物列表后的通知"; // 渠道描述
            int importance = NotificationManager.IMPORTANCE_DEFAULT; // 通知的重要性
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // 获取系统的通知管理器并注册渠道
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // 检查是否已经有权限
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // 如果没有权限，则发起请求
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }

    private void sendNotification(String title) {
        // 检查权限，确保在有权限时才发送通知
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // 再次检查权限，如果 Android 13+ 还没有权限，就不发送通知
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                Toast.makeText(this, "请先授予通知权限", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // 使用 NotificationCompat.Builder 构建通知
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher) // 设置小图标（必需），通常是应用图标
                .setContentTitle(title) // 设置通知标题，即列表项内容
                .setContentText("这是一个关于 " + title + " 的通知，点击查看详情。") // 设置通知内容
                .setPriority(NotificationCompat.PRIORITY_DEFAULT) // 设置优先级
                .setAutoCancel(true); // 用户点击通知后，通知自动消失

        // 使用 NotificationManagerCompat 发送通知
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // 使用随机数或 position 作为 notificationId，确保每条通知都是独立的
        // 如果使用固定ID，新通知会覆盖旧通知
        int notificationId = new Random().nextInt();
        notificationManager.notify(notificationId, builder.build());
    }

}
