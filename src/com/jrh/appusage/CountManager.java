package com.jrh.appusage;

import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class CountManager {
	private static ActivityManager mActivityManager;
	private static PackageManager mPackageManager;
	private static ApplicationInfo mApplicatonInfo;
	private static AppInfo mAppInfo;
	private static String mLabel;
	private static String mPkgName;

	/**
	 * GetTopActivity
	 * 
	 * @param context
	 * @return
	 */
	public static AppInfo getTopActivity(Context context) {
		mActivityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		mPackageManager = context.getPackageManager();
		List<RunningTaskInfo> tasksInfo = mActivityManager.getRunningTasks(1);
		if (tasksInfo.size() > 0) {
			mAppInfo = new AppInfo();
			mPkgName = tasksInfo.get(0).topActivity.getPackageName();
			try {
				mApplicatonInfo = mPackageManager.getApplicationInfo(mPkgName,
						PackageManager.GET_META_DATA);
				mLabel = (String) mApplicatonInfo.loadLabel(mPackageManager);
				mApplicatonInfo.loadIcon(mPackageManager);
				
				mAppInfo.setPkgname(mPkgName);
				mAppInfo.setLabel(mLabel);

			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
		}
		return mAppInfo;
	}
	
	
	/**
	 * To get all Apps which were installed by the user, in other words, they
	 * ARE NOT system apps
	 * 
	 * @param context
	 * @return
	 */
	public static List<AppInfo> getInstalledApps(Context context){
		List<AppInfo> appList = new ArrayList<AppInfo>();
		
		
		return appList;
	}
}