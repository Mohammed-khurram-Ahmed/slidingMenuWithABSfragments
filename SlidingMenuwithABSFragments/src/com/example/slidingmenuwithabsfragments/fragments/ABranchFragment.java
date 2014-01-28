package com.example.slidingmenuwithabsfragments.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragment;
import com.example.slidingmenuwithabsfragments.R;

public class ABranchFragment extends SherlockFragment implements ActionBar.TabListener  {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_branch_a, container,
				false);
		return rootView;
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		ft.add(android.R.id.content, this,"android");
        ft.attach(this);
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		 ft.detach(this);
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {

		
	}

}
