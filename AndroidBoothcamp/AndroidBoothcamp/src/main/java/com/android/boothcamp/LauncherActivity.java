package com.android.boothcamp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.boothcamp.database.DatabaseHelper;
import com.android.boothcamp.database.model.Scholar;

public class LauncherActivity extends Activity {
    public DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        dbHelper = new DatabaseHelper(LauncherActivity.this);
        insertData();

        Button btnClickMe = (Button) findViewById(R.id.btnClick);
        btnClickMe.setOnClickListener(clikMeListener);
    }

    private View.OnClickListener clikMeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String iNo = ((EditText) findViewById(R.id.etInput)).getText().toString();

            SQLiteDatabase db = dbHelper.getWritableDatabase();

            Cursor cursor = db.query(false, Scholar.TABLE_NAME, null, Scholar.INO + "=?", new String[]{iNo}, null, null, null, null);

            if(cursor.moveToFirst()) {
                    String retiNo = cursor.getString(cursor.getColumnIndex(Scholar.INO));
                    String retName = cursor.getString(cursor.getColumnIndex(Scholar.NAME));

                    ((TextView) findViewById(R.id.tvINo)).setText(retiNo);
                    ((TextView) findViewById(R.id.tvName)).setText(retName);
            } else {
                Toast.makeText(LauncherActivity.this, "No Scholar Found!!", Toast.LENGTH_LONG).show();
            }
        }
    };

    private void insertData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values1 = new ContentValues();
        values1.put(Scholar.INO, "I303377");
        values1.put(Scholar.NAME, "Ankur");

        ContentValues values2 = new ContentValues();
        values2.put(Scholar.INO, "I303363");
        values2.put(Scholar.NAME, "Archana");

        db.insert(Scholar.TABLE_NAME, null, values1);
        db.insert(Scholar.TABLE_NAME, null, values2);
    }
}