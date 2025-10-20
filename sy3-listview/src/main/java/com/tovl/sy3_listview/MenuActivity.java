package com.tovl.sy3_listview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MenuActivity extends AppCompatActivity {
    private TextView testTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_menu_xml);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);

        setSupportActionBar(myToolbar);

        testTextView = findViewById(R.id.test_text_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        // 1. 处理字体大小选项
        if (itemId == R.id.font_size_small) {
            testTextView.setTextSize(10);
            Toast.makeText(this, "字体已设为小号 (10sp)", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.font_size_medium) {
            testTextView.setTextSize(16);
            Toast.makeText(this, "字体已设为中号 (16sp)", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.font_size_large) {
            testTextView.setTextSize(20);
            Toast.makeText(this, "字体已设为大号 (20sp)", Toast.LENGTH_SHORT).show();
            return true;
        }
        // 2. 处理普通菜单项（弹出 Toast）
        else if (itemId == R.id.menu_show_toast) {
            Toast.makeText(this, "你点击了普通菜单项！", Toast.LENGTH_SHORT).show();
            return true;
        }
        // 3. 处理字体颜色选项
        else if (itemId == R.id.font_color_red) {
            testTextView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            Toast.makeText(this, "字体颜色已设为红色", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.font_color_black) {
            testTextView.setTextColor(getResources().getColor(android.R.color.black));
            Toast.makeText(this, "字体颜色已设为黑色", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

}
