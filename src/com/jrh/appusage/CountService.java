package com.jrh.appusage;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class CountService extends Service{
	private Handler handler = new Handler();
	private Timer timer;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (timer == null) {
			timer = new Timer();
			timer.scheduleAtFixedRate(new RefreshTask(), 0, 1000);
		}
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		// When Service is stopped,stop the Timer too.
		timer.cancel();
		timer = null;
	}
	
	class RefreshTask extends TimerTask {
		String curPkgName;
		@Override
		public void run() {
			handler.post(new Runnable() {
				@Override
				public void run() {
					AppInfoService appInfoService = new AppInfoService(getApplicationContext());
					AppInfo appInfo = CountManager.getTopActivity(getApplicationContext());
					
					if(!appInfo.getPkgname().equals(curPkgName)){
						//TopActivity is changed,which means related data in the database should be changed
						curPkgName = appInfo.getPkgname();
						appInfoService.dealWithIt(appInfo);
						appInfoService.closeDB();
					}
				}
			});
		}
	}
	
}