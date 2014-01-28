package com.example.slidingmenuwithabsfragments;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.example.slidingmenuwithabsfragments.adapters.SlidingMenuListAdapter;
import com.example.slidingmenuwithabsfragments.classes.SlidingMenuItem;
import com.example.slidingmenuwithabsfragments.fragments.ABranchFragment;
import com.example.slidingmenuwithabsfragments.fragments.AboutFragment;
import com.example.slidingmenuwithabsfragments.fragments.BBranchFragment;
import com.example.slidingmenuwithabsfragments.fragments.CBranchFragment;
import com.example.slidingmenuwithabsfragments.fragments.GalleryFragment;
import com.example.slidingmenuwithabsfragments.fragments.HomeFragment;
import com.example.slidingmenuwithabsfragments.fragments.UpdateFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends SherlockFragmentActivity {

	private SlidingMenu slidingMenu;
	private Context context;
	private boolean doubleBackToExitPressedOnce;

	private ArrayList<SlidingMenuItem> arrayList;
	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = MainActivity.this;
		addSlidingMenu();
		addActionBar();
		addHomeFragment();
	}

	private void addHomeFragment() {
		SherlockFragment fragment = new HomeFragment();
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.add(R.id.container_frameLayout, fragment).commit();
	}

	private void addSlidingMenu() {
		slidingMenu = new SlidingMenu(context);
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setMenu(R.layout.sliding_menu_layout);
		slidingMenu.attachToActivity(MainActivity.this,
				SlidingMenu.SLIDING_WINDOW);
		slidingMenu.setFadeDegree(0.35F);
		slidingMenu.setShadowDrawable(R.drawable.shadow);
		slidingMenu.setShadowWidth(30);
		slidingMenu.setBehindOffset(60);
		slidingMenu.setSlidingEnabled(true);

		papulateSlidingList();
	}

	private void addActionBar() {
		actionBar = getSupportActionBar();
		actionBar.setTitle("Home");
		actionBar.setHomeButtonEnabled(true);
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.abs__cab_background_top_holo_light));
		actionBar.setIcon(R.drawable.menu);
	}

	private void papulateSlidingList() {
		arrayList = new ArrayList<SlidingMenuItem>();
		arrayList.add(new SlidingMenuItem("Home", R.drawable.home, 5));
		arrayList.add(new SlidingMenuItem("Gallery", R.drawable.gallery, 1));
		arrayList.add(new SlidingMenuItem("Updates", R.drawable.update, 0));
		arrayList.add(new SlidingMenuItem("Branch", R.drawable.branches, 4));
		arrayList.add(new SlidingMenuItem("About", R.drawable.about, 4));

		ListView listView = (ListView) findViewById(R.id.list_slidermenu);
		SlidingMenuListAdapter adapter = new SlidingMenuListAdapter(context,
				arrayList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				displayView(position);
			}
		});
	}

	/**
	 * Diplaying fragment view for selected Sliding Menu list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		removeFragmentIfExist("android");
		removeFragmentIfExist("apple");
		removeFragmentIfExist("Blackb");

		SherlockFragment fragment = null;
		switch (position) {
		case 0:
			fragment = new HomeFragment();
			loadFragment(fragment, position);
			break;
		case 1:
			fragment = new GalleryFragment();
			loadFragment(fragment, position);

			break;
		case 2:
			fragment = new UpdateFragment();
			loadFragment(fragment, position);
			break;
		case 3:
			loadTabFragment(fragment, position);
			break;
		case 4:
			fragment = new AboutFragment();
			loadFragment(fragment, position);
			break;
		default:
			break;
		}

	}

	private void removeFragmentIfExist(String name) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		SherlockFragment mFragment = (SherlockFragment) fragmentManager
				.findFragmentByTag(name);
		if (mFragment != null) { // && !mFragment.isDetached()) {
			FragmentTransaction fragmentTransaction = fragmentManager
					.beginTransaction();
			// ft.detach(mFragment);
			fragmentTransaction.remove(mFragment);
			fragmentTransaction.commit();
		}

	}

	private void loadTabFragment(SherlockFragment fragment, int position) {

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.removeAllTabs();
		Tab tab = actionBar.newTab().setText("Android")
				.setTabListener(new ABranchFragment())
				.setIcon(R.drawable.ic_launcher);
		actionBar.addTab(tab);
		tab = actionBar.newTab().setText("Apple")
				.setTabListener(new BBranchFragment())
				.setIcon(R.drawable.ic_launcher);
		actionBar.addTab(tab);
		tab = actionBar.newTab().setText("Black B")
				.setTabListener(new CBranchFragment())
				.setIcon(R.drawable.ic_launcher);
		actionBar.addTab(tab);
		slidingMenu.toggle();

	}

	private void loadFragment(SherlockFragment fragment, int position) {
		if (fragment != null) {
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager
					.beginTransaction();

			fragmentTransaction.replace(R.id.container_frameLayout, fragment)
					.commit();
			// update selected item and title, then close the drawer
			actionBar.setTitle(arrayList.get(position).title.toString());
			slidingMenu.toggle();
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			slidingMenu.toggle();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Double tap to exit
	 */

	@Override
	public void onBackPressed() {
		if (doubleBackToExitPressedOnce) {
			super.onBackPressed();
			return;
		}
		this.doubleBackToExitPressedOnce = true;
		Toast.makeText(this, "Please click BACK again to exit",
				Toast.LENGTH_SHORT).show();
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				doubleBackToExitPressedOnce = false;

			}
		}, 2000);
	}
}
