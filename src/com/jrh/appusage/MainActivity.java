package com.jrh.appusage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ListView mListView;
	private ListAdapter mListAdapter;
	private TextView mTitle, mTotal;
	private Context mContext;
	AppInfoService appInfoService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		initUI();
		
		Intent intent = new Intent(MainActivity.this,CountService.class);
		startService(intent);
		
		appInfoService = new AppInfoService(mContext);
		mListAdapter = new ListAdapter(mContext,appInfoService.getAllApps());
		mListView.setAdapter(mListAdapter);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mListAdapter.changed(appInfoService.getAllApps());
		
	}
	
	private void initUI(){
		mTitle = (TextView) findViewById(R.id.app_name);
		mTotal = (TextView) findViewById(R.id.total);
		mListView = (ListView) findViewById(R.id.listView1);
	}
}