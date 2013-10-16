package com.android.boothcamp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.boothcamp.database.DatabaseHelper;
import com.android.boothcamp.database.model.Scholar;

public class LauncherActivity extends Activity {
    public DatabaseHelper dbHelper;

    public Button btnClickMe;
    public ListView scholarList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        dbHelper = new DatabaseHelper(LauncherActivity.this);
        insertData();

        btnClickMe = (Button) findViewById(R.id.btnClick);
        scholarList = (ListView) findViewById(R.id.scholarsList);

        btnClickMe.setOnClickListener(clikMeListener);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(false, Scholar.TABLE_NAME, null, null, null, null, null, null, null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(LauncherActivity.this, R.layout.row_template, cursor, new String[] { Scholar.INO, Scholar.NAME}, new int[] { R.id.tvINo, R.id.tvName }, CursorAdapter.NO_SELECTION);
        scholarList.setAdapter(adapter);
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