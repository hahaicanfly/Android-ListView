package com.akira.advenceui;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by akira on 2016/6/7.
 */

//	政府提供的旅館OpenData　JSON資料 讀進來 放到ListView中
public class MyListView extends ListActivity {

	private Context context;
	private MyAdapter adapter;
	private ArrayList<Hotel> myArrayList = new ArrayList<Hotel>();
	private String AreaSelected;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		
		new Thread(new ProgressRunnable(context)).start();

		Intent intent = getIntent();
		AreaSelected = intent.getStringExtra("Area");

		ReadJson();

		// adapter = new MyAdapter(context,myArrayList);
		// setListAdapter(adapter);
		// //onClick
		// getListView().setOnItemClickListener(new MyOnClickListener());
	}

	public void ReadJson() {

		try {
			String json = getRawData(context, R.raw.hoteldata).toString();
			JSONArray respJArray = new JSONArray(json);
			parseJSONArrayToList(respJArray);

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	// .json file to JSONArray
	public JSONArray getRawData(Context context, int res_id) {
		InputStream is = null;
		InputStreamReader reader = null;
		StringBuilder sb = new StringBuilder();
		JSONArray respJSONArray = null;
		try {
			is = context.getResources().openRawResource(res_id);
			reader = new InputStreamReader(is, "UTF-8");
			char[] buffer = new char[1024];

			while (reader.read(buffer) != -1) {
				sb.append(new String(buffer));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
				final String resp = sb.toString();
				respJSONArray = new JSONArray(resp);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return respJSONArray;
	}

	public void parseJSONArrayToList(JSONArray content) {
		try {
			for (int i = 0; i < content.length(); i++) {
				JSONObject hotel = content.getJSONObject(i);

				String Title = hotel.getString("stitle");
				String Address = hotel.getString("address");
				String Tel = hotel.getString("memo_tel");

				// 座標
				String Y = hotel.getString("longitude");
				String X = hotel.getString("latitude");

				Hotel mhotel = new Hotel(Title, Address, Tel, Y, X);

				if (Address.contains(AreaSelected)) {
					myArrayList.add(mhotel);
				}

			}


			adapter = new MyAdapter(context, myArrayList);
			setListAdapter(adapter);
			// onClick
			getListView().setOnItemClickListener(new MyOnClickListener());

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	private class MyOnClickListener implements AdapterView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			Hotel hotel = (Hotel) parent.getItemAtPosition(position);

			String msg = hotel.getTitle() + " " + hotel.getAddress() + " " + hotel.getTel() + " " + hotel.getY() + " "
					+ hotel.getX();

			String X = (String) hotel.getX();
			String Y = (String) hotel.getY();
			String title = (String) hotel.getTitle();
			String Tel = "";
			if (!hotel.getTel().equals("")) {
				Tel = (String) hotel.getTel();
			} else {
				Tel = "無電話資訊";
			}
			Log.i("Test", "選擇了: "+ msg);

		}
	}


}
