package com.tovl.sy3_listview;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class ActionModeActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_mode);

        listView = findViewById(R.id.my_list_view);

        // 1. 准备数据
        items = new ArrayList<>(Arrays.asList(
                "One", "Two", "Three", "Four",
                "Five", "Six", "Seven", "Eight",
                "Nine", "Ten"
        ));
        // 2. 初始化 Adapter
        adapter = new ArrayAdapter<>(
                this,
                R.layout.action_item_am,
                R.id.text_am,
                items
        );
        listView.setAdapter(adapter);
        // 3. 设置 ListView 的选择模式为 MULTIPLE_MODAL (多选模式)
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        // 4. 设置 MultiChoiceModeListener
        listView.setMultiChoiceModeListener(new MyMultiChoiceModeListener());
    }

    private class MyMultiChoiceModeListener implements AbsListView.MultiChoiceModeListener {
        private ActionMode currentActionMode;

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.action_mode_menu, menu); // 加载自定义菜单
            currentActionMode = mode;
            return true;
        }

        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
            // 获取当前选中的项目数
            int checkedCount = listView.getCheckedItemCount();
            // 更新 ActionMode 顶部的标题
            mode.setTitle(checkedCount + " Selected");
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (item.getItemId() == R.id.action_delete) {
                // 执行删除操作

                // 1. 获取所有选中的项目的位置
                SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();

                // 2. 遍历并删除选中的项目
                for (int i = checkedItemPositions.size() - 1; i >= 0; i--) {
                    if (checkedItemPositions.valueAt(i)) {
                        int position = checkedItemPositions.keyAt(i);
                        // 从数据源中移除该项目
                        items.remove(position);
                    }
                }

                // 3. 通知 Adapter 数据已更改
                adapter.notifyDataSetChanged();

                // 4. 结束 ActionMode 模式
                mode.finish();

                Toast.makeText(ActionModeActivity.this, "已删除选中的项目", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            currentActionMode = null;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }
    }
}
