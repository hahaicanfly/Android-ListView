package com.akira.advenceui;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by akira on 2016/6/7.
 */

// 	ListView 與資料的適配器
public class MyAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<Hotel> list;

	public MyAdapter(Context context, ArrayList<Hotel> list) {
		mInflater = LayoutInflater.from(context);
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.row, null);

			holder = new ViewHolder();
			holder.Title = (TextView) convertView.findViewById(R.id.tvTitle);
			holder.Address = (TextView) convertView.findViewById(R.id.tvAddress);
			holder.Tel = (TextView) convertView.findViewById(R.id.tvTel);

			convertView.setTag(holder);
		} else {

			holder = (ViewHolder) convertView.getTag();
		}

		Hotel hotel = list.get(position);

		holder.Title.setText(hotel.getTitle());

		holder.Address.setText(hotel.getAddress());

		if (!hotel.getTel().equals("")) {
			String allTel = hotel.getTel();
			String[] firstTel = allTel.split(" ");
			Log.i("AAAAAAAA", Arrays.toString(firstTel));
			holder.Tel.setText(firstTel[0]);
			// holder.Tel.setText(hotel.getTel());
		} else {
			holder.Tel.setText("無電話資訊");
		}
		return convertView;
	}

	static class ViewHolder {
		TextView Title;
		TextView Address;
		TextView Tel;
	}

}
