package com.example.testtemplate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MainActivity extends FragmentActivity {
	private PlaceholderFragment placeHolderFragment;
	private static String KEY_XML_NAME="";
	/**xml 文件名称*/
	public String xmlName="";
	public static Intent newIntent(Activity  act,String xmlName){
		Intent intent = new Intent(act,MainActivity.class);
		intent.putExtra(KEY_XML_NAME, xmlName);
		return intent;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		xmlName=getIntent().getStringExtra(KEY_XML_NAME);
		if (savedInstanceState == null) {
			PlaceholderFragment placeholderFragment2 = new PlaceholderFragment();
			getSupportFragmentManager().beginTransaction().add(R.id.container, placeholderFragment2, "tag").commit();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		int id = item.getItemId();
//		placeHolderFragment = (PlaceholderFragment) getSupportFragmentManager()
//				.findFragmentByTag("tag");
//		if (id == R.id.action_mode1) {
//			placeHolderFragment.testConfigRead.setMode(1);
//			placeHolderFragment.initData();
//			return true;
//		} else if (id == R.id.action_mode2) {
//			placeHolderFragment.testConfigRead.setMode(0);
//			placeHolderFragment.initData();
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
	@Override
	protected void onDestroy() {
		RequestQueue queue = Volley.newRequestQueue(this);
		queue.cancelAll(this);
		super.onDestroy();
	}
}
