package com.jrh.appusage;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AppInfoService {
	private DBOpenHelper dbOpenHelper;

	public AppInfoService(Context context) {

		this.dbOpenHelper = new DBOpenHelper(context);
	}

	/**
	 * Query the database according to the given parameter,
	 * if there is no record, do the insert, and total is 1
	 * if there is a record,total should be total+1
	 * @param appInfo
	 */
	public void dealWithIt(AppInfo appInfo) {
		SQLiteDatabase dbRead = dbOpenHelper.getReadableDatabase();
		SQLiteDatabase dbWrite = dbOpenHelper.getWritableDatabase();
		Cursor cursor = dbRead.rawQuery("select total from appInfo where pkgName=?",
				new String[] { appInfo.getPkgname() });
		if (cursor.moveToFirst()) {
			int total = cursor.getInt(cursor.getColumnIndex("total"));
			dbWrite.execSQL("update appInfo set total=? where pkgName=?",
					new Object[] {total+1, appInfo.getPkgname() });
			cursor.close();
			
		}else{
			dbWrite.execSQL(
					"insert into appInfo(pkgName,label,total) values(?,?,?)",
					new Object[] { appInfo.getPkgname(),appInfo.getLabel(), appInfo.getTotal() });
		}		
	}

	/**
	 * Get the data from from database for ListView
	 * @return
	 */
	public List<AppInfo> getAllApps() {
		List<AppInfo> appList = new ArrayList<AppInfo>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase(); 
		Cursor cursor = db.rawQuery(
				"select * from appInfo order by total asc",null);
		while (cursor.moveToNext()) {
			String pname = cursor.getString(cursor.getColumnIndex("pkgName"));
			String label = cursor.getString(cursor.getColumnIndex("label"));
			int total = cursor.getInt(cursor.getColumnIndex("total"));
			appList.add(new AppInfo( pname, label, total));
		}
		cursor.close();
		return appList;
	}
	
	public void closeDB(){
		dbOpenHelper.close();
	}
}