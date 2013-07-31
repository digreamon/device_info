package com.digreamon.app.deviceinfo;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	ListView deviceInfoList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		deviceInfoList = (ListView)findViewById(R.id.deviceInfoList);
		
		deviceInfoList.addHeaderView(
				DeviceInfoAdapter.getHeaderView(this));
		deviceInfoList.setAdapter(
				DeviceInfoAdapter.getDeviceInfoAdapter(this));
	}

	public static class DeviceInfoAdapter extends ArrayAdapter<String> {

		private final static String DEVICE_INFO_NAME_HEAD = "Name";
		private final static String DEVICE_INFO_VALUE_HEAD = "Value";
		
		public static View getHeaderView(Context context){
			View view = LayoutInflater.from(context).inflate(R.layout.list_header_layout, null);
			TextView name_head = (TextView)view.findViewById(R.id.name_head);
			TextView value_head = (TextView)view.findViewById(R.id.value_head);
			view.setBackgroundColor(Color.LTGRAY);
			name_head.setText(DEVICE_INFO_NAME_HEAD);
			value_head.setText(DEVICE_INFO_VALUE_HEAD);
			return view;
		}
		
		public static DeviceInfoAdapter getDeviceInfoAdapter(Context context){
			LinkedHashMap<String, Object> table = new LinkedHashMap<String, Object>();
			
			table.put("CalendarMillis", Calendar.getInstance().getTimeInMillis());
			table.put("BOARD", Build.BOARD);
			table.put("BOOTLOADER", Build.BOOTLOADER);
			table.put("BRAND", Build.BRAND);
			table.put("CPU_ABI", Build.CPU_ABI);
			table.put("CPU_ABI2", Build.CPU_ABI2);
			table.put("DEVICE", Build.DEVICE);
			table.put("DISPLAY", Build.DISPLAY);
			table.put("FINGERPRINT", Build.FINGERPRINT);
			table.put("HARDWARE", Build.HARDWARE);
			table.put("HOST", Build.HOST);
			table.put("ID", Build.ID);
			table.put("MANUFACTURER", Build.MANUFACTURER);
			table.put("MODEL", Build.MODEL);
			table.put("PRODUCT", Build.PRODUCT);
			table.put("SERIAL", Build.SERIAL);
			table.put("TAGS", Build.TAGS);
			table.put("TIME", Build.TIME);
			table.put("TYPE", Build.TYPE);
			table.put("USER", Build.USER);
			
			table.put("VERSION.CODENAME", Build.VERSION.CODENAME);
			table.put("VERSION.RELEASE", Build.VERSION.RELEASE);
			
			return new DeviceInfoAdapter(context, table);
		}
		
		HashMap<String, Object> table;
		
		private DeviceInfoAdapter(Context context, HashMap<String, Object> table) {
			super(context, R.layout.list_item_layout, 
					R.id.title, table.keySet().toArray(new String[]{}));
			this.table = table;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				convertView = LayoutInflater.from(
						getContext()).inflate(R.layout.list_item_layout, null);
			}
			
			convertView.setBackgroundColor(((position%2==0)?Color.argb(80, 173, 255, 47):Color.argb(80, 50, 205, 50)));
			
			TextView title = (TextView)convertView.findViewById(R.id.title);
			TextView value = (TextView)convertView.findViewById(R.id.value);
			
			String key = getItem(position);
			title.setText(key);
			value.setText(String.valueOf(table.get(key)));
			
			return super.getView(position, convertView, parent);
		}
	}
}
