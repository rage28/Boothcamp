package com.android.boothcamp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Button btnClickMe = (Button) findViewById(R.id.btnClick);
        btnClickMe.setOnClickListener(clikMeListener);
    }

    private View.OnClickListener clikMeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent("com.android.boothcamp.SECONDACTIVITY");
            startActivity(intent);
        }
    };
}