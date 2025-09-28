package com.tovl.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void clickHandler(View source) {
        TextView tv = findViewById(R.id.show);
        tv.setText("Hello Android-" + new java.util.Date());
    }
}
