package com.jrh.appusage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenActionReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		if (action.equals(Intent.ACTION_SCREEN_ON)) {
			//开启监控服务...
		} else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
			//关闭监控服务...
		}
	}
}