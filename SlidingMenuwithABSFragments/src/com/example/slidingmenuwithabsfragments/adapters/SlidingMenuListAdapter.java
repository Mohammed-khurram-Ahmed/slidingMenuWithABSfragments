package com.example.slidingmenuwithabsfragments.adapters;

import java.util.ArrayList;

import com.example.slidingmenuwithabsfragments.R;
import com.example.slidingmenuwithabsfragments.R.id;
import com.example.slidingmenuwithabsfragments.R.layout;
import com.example.slidingmenuwithabsfragments.classes.SlidingMenuItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SlidingMenuListAdapter extends BaseAdapter {
	ArrayList<SlidingMenuItem> arrayList;
	Context context;

	public SlidingMenuListAdapter(Context context,
			ArrayList<SlidingMenuItem> arrayList) {
		super();
		this.arrayList = arrayList;
		this.context = context;
	}

	@Override
	public int getCount() {
		return arrayList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return arrayList.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.sliding_menu_item_layout,
					null);
		}

		ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
		TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
		TextView txtCount = (TextView) convertView.findViewById(R.id.counter);
		imgIcon.setImageResource(arrayList.get(position).iconResId);
		txtTitle.setText(arrayList.get(position).title);
		// displaying count
		// check whether it set visible or not
		
		txtCount.setText(arrayList.get(position).count.toString());
//		if (arrayList.get(position).count > 0) {
//		
//		} else {
//			// hide the counter view
//			txtCount.setVisibility(View.GONE);
//		}

		return convertView;
	}

}
