package com.example.repasoexamenandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NOMBRE = "db_VehiculoSQLite";
    private static final int DATABASE_VERSION = 1;

    String SQLCREATE = "CREATE TABLE vehiculo (matricula TEXT PRIMARY KEY, marca TEXT NOT NULL, modelo TEXT NOT NULL)";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLCREATE);
        db.execSQL("INSERT INTO vehiculo (matricula, marca, modelo) VALUES ('1488DMM','Toyota','Yaris'),('0000KKK','Toyota','Supra'),('1234ABC','Nissan','Qashqai')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS vehiculo");
        onCreate(db);
    }
}
