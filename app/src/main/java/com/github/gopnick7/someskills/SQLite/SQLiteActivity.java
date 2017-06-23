package com.github.gopnick7.someskills.SQLite;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.gopnick7.someskills.R;


public class SQLiteActivity extends Activity implements View.OnClickListener {

    EditText edtId, edtName, edtAge, edtAddress, edtSalary;
    Button btnInsert, btnUpdate, btnDelete, btnShow;
    TextView txtShowData;

    DataBase dataBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_activity);

        init();

        dataBase = new DataBase(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnInsert:
                dataBase.insert(
                        getValue(edtName),
                        Integer.parseInt(getValue(edtAge)),
                        getValue(edtAddress),
                        Double.valueOf(getValue(edtSalary)));
                break;
            case R.id.btnUpdate:
                dataBase.update(
                        Integer.parseInt(getValue(edtId)),
                        getValue(edtName),
                        Integer.parseInt(getValue(edtAge)),
                        getValue(edtAddress),
                        Double.valueOf(getValue(edtSalary)));
                break;
            case R.id.btnDelete:
                dataBase.delete(
                        Integer.parseInt(getValue(edtId)));
                break;
            case R.id.btnShow:
                Cursor cursor = dataBase.showAllData();
                StringBuffer sb = new StringBuffer();

                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    sb.append(cursor.getInt(cursor.getColumnIndex(DataBase.ID)));
                    sb.append(" | ");
                    sb.append(cursor.getString(cursor.getColumnIndex(DataBase.NAME)));
                    sb.append(" | ");
                    sb.append(cursor.getInt(cursor.getColumnIndex(DataBase.AGE)));
                    sb.append(" | ");
                    sb.append(cursor.getString(cursor.getColumnIndex(DataBase.ADDRESS)));
                    sb.append(" | ");
                    sb.append(cursor.getDouble(cursor.getColumnIndex(DataBase.SALARY)));
                    sb.append("\n");
                }

                txtShowData.setText(sb);

                break;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        dataBase.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dataBase.closeDB();
    }

    private String getValue(EditText edt) {
        return edt.getText().toString().trim();
    }

    private void init() {

        edtId = (EditText) findViewById(R.id.edtId);
        edtName = (EditText) findViewById(R.id.edtName);
        edtAge = (EditText) findViewById(R.id.edtAge);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtSalary = (EditText) findViewById(R.id.edtSalary);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnShow = (Button) findViewById(R.id.btnShow);

        btnInsert.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnShow.setOnClickListener(this);

        txtShowData = (TextView) findViewById(R.id.txtShowData);

    }

}
