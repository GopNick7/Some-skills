package com.github.gopnick7.someskills.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

 class DataBase extends SQLiteOpenHelper {

    private static final String DB_NAME = "230617";
    private static final int VERSION = 1;

    private static final String TABLE_NAME = "employee";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String ADDRESS = "address";
    public static final String SALARY = "salary";

    private SQLiteDatabase sqLiteDatabase;

    DataBase(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create = "CREATE TABLE " + TABLE_NAME +
                " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT NOT NULL, " +
                AGE + " INTEGER NOT NULL, " +
                ADDRESS + " TEXT NOT NULL, " +
                SALARY + " REAL NOT NULL " +
                ")";
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void openDB() {
        sqLiteDatabase = getWritableDatabase();
    }

    void closeDB() {
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

    long insert(String name, int age, String address, Double salary) {
        ContentValues cv = new ContentValues();

        cv.put(NAME, name);
        cv.put(AGE, age);
        cv.put(ADDRESS, address);
        cv.put(SALARY, salary);

        return sqLiteDatabase.insert(TABLE_NAME, null, cv);
    }

    long update(int id, String name, int age, String address, Double salary) {
        ContentValues cv = new ContentValues();

        cv.put(NAME, name);
        cv.put(AGE, age);
        cv.put(ADDRESS, address);
        cv.put(SALARY, salary);

        String where = ID + " = " + id;

        return sqLiteDatabase.update(TABLE_NAME, cv, where, null);
    }

    long delete(int id) {
        String where = ID + " = " + id;

        return sqLiteDatabase.delete(TABLE_NAME, where, null);
    }

    Cursor showAllData() {

        String sql = "SELECT * FROM " + TABLE_NAME;
        return sqLiteDatabase.rawQuery(sql, null);
    }
}
