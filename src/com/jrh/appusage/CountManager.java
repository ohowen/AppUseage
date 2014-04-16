package com.jrh.appusage;

import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

public class CountManager {
	private static ActivityManager mActivityManager;
	private static PackageManager mPackageManager;
	private static ApplicationInfo mApplicatonInfo;
	private static List<RecentTaskInfo> mRecentTaskInfoList;
	private static List<AppInfo> mAppList;
	private static AppInfo mAppInfo;
	private static String mLabel;
	private static Drawable mImg;
	private static String mPkgName;

	/**
	 * ��ȡ��ǰ����ʹ�õ�app
	 * 
	 * @param context
	 * @return
	 */
	public static AppInfo getTopActivity(Context context) {
		mActivityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		mPackageManager = context.getPackageManager();
		mAppList = new ArrayList<AppInfo>();
		List<RunningTaskInfo> tasksInfo = mActivityManager.getRunningTasks(1);
		if (tasksInfo.size() > 0) {
			mAppInfo = new AppInfo();
			mPkgName = tasksInfo.get(0).topActivity.getPackageName();
			try {
				mApplicatonInfo = mPackageManager.getApplicationInfo(mPkgName,
						PackageManager.GET_META_DATA);
				mLabel = (String) mApplicatonInfo.loadLabel(mPackageManager);
				mImg = mApplicatonInfo.loadIcon(mPackageManager);
				
				mAppInfo.setPkgname(mPkgName);
				mAppInfo.setLabel(mLabel);

			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
		}
		//System.out.println("����:" + mPkgName + "Ӧ����" + mLabel);
		return mAppInfo;
	}

	/**
	 * ��ȡ���ʹ�õ�Ӧ��
	 * 
	public static List<AppInfo> getRecentActivity(Context context) {
		mActivityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		mPackageManager = context.getPackageManager();
		mAppList = new ArrayList<AppInfo>();
		try {
			mRecentTaskInfoList = mActivityManager.getRecentTasks(10,
					ActivityManager.RECENT_IGNORE_UNAVAILABLE);
			for (ActivityManager.RecentTaskInfo info : mRecentTaskInfoList) {
				Intent intent = info.baseIntent;
				if (intent.getAction() == null) {
					String pkgName = intent.getComponent().getPackageName();
					intent = mPackageManager.getLaunchIntentForPackage(pkgName);
				}
				// Log.i(TAG, "getLatestApp()-intent:" + intent);
				// ��launcher�����Ӧ�ñ����ӻ�ȡ�������ʹ�õ�Ӧ���б��й��˵�
				if (Intent.ACTION_MAIN.equals(intent.getAction())
						&& !intent.hasCategory(Intent.CATEGORY_HOME)
						&& !context.getPackageName().equals(
								intent.getComponent().getPackageName())) {
					ResolveInfo resolveInfo = mPackageManager.resolveActivity(
							intent, 0);
					if (resolveInfo != null) {
						mAppInfo = new AppInfo();
						mAppInfo.setImg(resolveInfo.activityInfo
								.loadIcon(mPackageManager));
						mAppInfo.setPkgname(intent.getComponent()
								.getPackageName());
						mAppInfo.setLabel((String) resolveInfo
								.loadLabel(mPackageManager));
						mAppList.add(mAppInfo);
					}
				}
				// ����ȡ4�����ʹ�ù���Ӧ��
				if (mAppList.size() == 4)
					break;
			}
		} catch (SecurityException se) {
			se.printStackTrace();
		}
		return mAppList;
	}*/
}