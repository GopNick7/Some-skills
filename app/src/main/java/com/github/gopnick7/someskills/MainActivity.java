package com.github.gopnick7.someskills;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.gopnick7.someskills.JSON.JSONActivity;
import com.github.gopnick7.someskills.JSOUP.JSOUPActivity;
import com.github.gopnick7.someskills.SQLite.SQLiteActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSQLite, btnJSON, btnJSOUP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    @Override
    public void onClick(View v) {
        Intent i = null;
        switch (v.getId()) {
            case R.id.btnSQLite:
                i = new Intent(MainActivity.this, SQLiteActivity.class);
                break;
            case R.id.btnJSON:
                i = new Intent(MainActivity.this, JSONActivity.class);
                break;
            case R.id.btnJSOUP:
                i = new Intent(MainActivity.this, JSOUPActivity.class);
                break;

        }
        startActivity(i);
    }

    private void init() {

        btnSQLite = (Button) findViewById(R.id.btnSQLite);
        btnJSON = (Button) findViewById(R.id.btnJSON);
        btnJSOUP = (Button) findViewById(R.id.btnJSOUP);

        btnSQLite.setOnClickListener(this);
        btnJSON.setOnClickListener(this);
        btnJSOUP.setOnClickListener(this);

    }

}