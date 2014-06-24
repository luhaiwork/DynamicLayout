package com.example.testtemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class StartActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		@InjectView(R.id.btn_show_type1)
		Button btn_show_type1;
		@InjectView(R.id.btn_show_type2)
		Button btn_show_type2;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_start,
					container, false);
			ButterKnife.inject(this, rootView);
			return rootView;
		}
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
		}
		@Override
		public void onDestroyView() {
			ButterKnife.reset(this);
			super.onDestroyView();
		}
		@OnClick(R.id.btn_show_type1)
		public void btn_show_type1(Button btn_show_type1){
			Intent intent  = new Intent(getActivity(),MainActivity.class);
			getActivity().startActivity(intent);
		}
		@OnClick(R.id.btn_show_type2)
		public void btn_show_type2(Button btn_show_type2){
			getActivity().startActivity(MainTypeActivity.newIntent(getActivity(), "page_config.xml"));
		}
		@OnClick(R.id.btn_show_type3)
		public void btn_show_type3(Button btn_show_type3){
			getActivity().startActivity(MainTypeActivity.newIntent(getActivity(), "page_config_1.xml"));
		}
		@OnClick(R.id.btn_show_type4)
		public void btn_show_type4(Button btn_show_type4){
			getActivity().startActivity(MainTypeActivity.newIntent(getActivity(), "page_config_2.xml"));
		}
		@OnClick(R.id.btn_show_type5)
		public void btn_show_type5(Button btn_show_type5){
			getActivity().startActivity(MainTypeActivity.newIntent(getActivity(), "page_config_3.xml"));
		}
		@OnClick(R.id.btn_show_type6)
		public void btn_show_type6(Button btn_show_type6){
			getActivity().startActivity(MainTypeActivity.newIntent(getActivity(), "page_config_4.xml"));
		}

	}

}
