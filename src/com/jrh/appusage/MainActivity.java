package com.jrh.appusage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemSelectedListener{
	private Spinner mSpinner;
	private ArrayAdapter<CharSequence> mArrayAdapter;
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
		
		mArrayAdapter = ArrayAdapter.createFromResource(this,
				R.array.mode_array, android.R.layout.simple_spinner_item);
		mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSpinner.setAdapter(mArrayAdapter);
		
		appInfoService = new AppInfoService(mContext);
		mListAdapter = new ListAdapter(mContext,appInfoService.getAllApps());
		mListView.setAdapter(mListAdapter);
		
		Intent intent = new Intent(MainActivity.this,CountService.class);
		startService(intent);
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
		mListView = (ListView) findViewById(R.id.applist);
		mSpinner = (Spinner) findViewById(R.id.mode_spinner);
		mSpinner.setOnItemSelectedListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		String item = parent.getItemAtPosition(position).toString();
		Toast.makeText(this, item, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
	}
}