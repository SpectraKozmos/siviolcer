package com.semihyavuz.siviolcer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fluids.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE fluids (id INTEGER PRIMARY KEY, name TEXT, density REAL, weight_per_liter REAL)");
        insertFluid(db, "Su", 1.0, 1.0);
        insertFluid(db, "Mercury", 13.6, 13.6);
        insertFluid(db, "Ethanol", 0.8, 0.8);
        insertFluid(db, "Yağ", 0.9, 0.9);
        insertFluid(db, "Benzin", 0.74, 0.74);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS fluids");
        onCreate(db);
    }

    private void insertFluid(SQLiteDatabase db, String name, double density, double weightPerLiter) {
        SQLiteStatement statement = db.compileStatement("INSERT INTO fluids (name, density, weight_per_liter) VALUES (?, ?, ?)");
        statement.bindString(1, name);
        statement.bindDouble(2, density);
        statement.bindDouble(3, weightPerLiter);
        statement.executeInsert();
    }

    public String findFluidByDensityAndWeight(double density, double weightPerLiter) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM fluids WHERE density = ? AND weight_per_liter = ?",
                new String[]{String.valueOf(density), String.valueOf(weightPerLiter)});
        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        } else {
            return "Bilinmeyen sıvı";
        }
    }
}
