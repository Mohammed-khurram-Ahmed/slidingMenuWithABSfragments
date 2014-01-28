package com.example.slidingmenuwithabsfragments;

import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class MainActivity extends SherlockFragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addSlidingMenu();
		addActionBar();

	}

	private void addSlidingMenu() {

	}

	private void addActionBar() {
		ActionBar topBar = getSupportActionBar();
		topBar.setTitle("Top Bar Title");
		topBar.setTitle(R.drawable.abs__cab_background_top_holo_light);
		
	}

}
