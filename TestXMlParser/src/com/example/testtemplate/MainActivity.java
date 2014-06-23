package com.example.testtemplate;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	private PlaceholderFragment placeHolderFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			PlaceholderFragment placeholderFragment2 = new PlaceholderFragment();
			getFragmentManager().beginTransaction()
					.add(R.id.container, placeholderFragment2, "tag").commit();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		placeHolderFragment = (PlaceholderFragment) getFragmentManager()
				.findFragmentByTag("tag");
		if (id == R.id.action_mode1) {
			placeHolderFragment.testConfigRead.setMode(1);
			placeHolderFragment.initData();
			return true;
		} else if (id == R.id.action_mode2) {
			placeHolderFragment.testConfigRead.setMode(0);
			placeHolderFragment.initData();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onDestroy() {
		RequestQueue queue = Volley.newRequestQueue(this);
		queue.cancelAll(this);
		super.onDestroy();
	}
}
