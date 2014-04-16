package com.jrh.appusage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	public DBOpenHelper(Context context) {
		super(context, "appcount.db",null,1); 
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE appInfo(appid integer primary key autoincrement, pkgName varchar(40), label VARCHAR(12),total integer)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
}