package com.example.slidingmenuwithabsfragments.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.slidingmenuwithabsfragments.fragments.ABranchFragment;
import com.example.slidingmenuwithabsfragments.fragments.BBranchFragment;
import com.example.slidingmenuwithabsfragments.fragments.CBranchFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

	// Declare the number of ViewPager pages
	final int PAGE_COUNT = 4;

	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {

		// Open FragmentTab1.java
		case 0:
			ABranchFragment fA = new ABranchFragment();
			return fA;
			// Open FragmentTab2.java
		case 1:
			BBranchFragment fB = new BBranchFragment();
			return fB;

			// Open FragmentTab3.java
		case 2:
			CBranchFragment fC = new CBranchFragment();
			return fC;
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return PAGE_COUNT;
	}

}