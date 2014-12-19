package com.example.uactivity;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import com.example.uactivity.bean.ActivityBean;
import com.example.uactivity.parser.ActivityParser;

import android.app.ProgressDialog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;



public class SettingActivity extends Activity {

	private Button buttonMyInfo;
	private Button buttonMyJoinIn;
//	ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(android.view.Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_list);
        getWindow().setFeatureInt(android.view.Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

		setContentView(R.layout.activity_setting);
		buttonMyInfo = (Button) findViewById(R.id.buttonSettingPerson);
		buttonMyJoinIn = (Button)findViewById(R.id.buttonSettingMyActivity);
		buttonMyInfo.setOnClickListener(clickListener);
		buttonMyJoinIn.setOnClickListener(clickListener);
	}
	
	
	private OnClickListener clickListener = new OnClickListener(){
		public void onClick(View v){
			// if button Register is clicked, skip to register1Activity
			if(v == buttonMyInfo){
	 			Intent intent = new Intent(SettingActivity.this,Register3Activity.class);
	 			SettingActivity.this.startActivity(intent);
			}
			
			if(v == buttonMyJoinIn){
			}
		}
		
	};

	
}
